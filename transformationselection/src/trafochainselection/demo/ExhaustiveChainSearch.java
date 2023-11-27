package trafochainselection.demo;

import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;

import at.ac.tuwien.big.moea.SearchAnalysis;
import at.ac.tuwien.big.moea.SearchExperiment;
import at.ac.tuwien.big.moea.SearchResultManager;
import at.ac.tuwien.big.moea.experiment.analyzer.SearchAnalyzer;
import at.ac.tuwien.big.moea.experiment.executor.SearchExecutor;
import at.ac.tuwien.big.moea.experiment.executor.listener.SeedRuntimePrintListener;
import at.ac.tuwien.big.moea.print.IPopulationWriter;
import at.ac.tuwien.big.moea.print.ISolutionWriter;
import at.ac.tuwien.big.moea.search.algorithm.EvolutionaryAlgorithmFactory;
import at.ac.tuwien.big.moea.search.algorithm.LocalSearchAlgorithmFactory;
import at.ac.tuwien.big.moea.search.algorithm.provider.AbstractRegisteredAlgorithm;
import at.ac.tuwien.big.moea.search.algorithm.provider.IRegisteredAlgorithm;
import at.ac.tuwien.big.moea.search.fitness.dimension.IFitnessDimension;
import at.ac.tuwien.big.momot.ModuleManager;
import at.ac.tuwien.big.momot.TransformationResultManager;
import at.ac.tuwien.big.momot.TransformationSearchOrchestration;
import at.ac.tuwien.big.momot.TransformationStateExplorer;
import at.ac.tuwien.big.momot.problem.solution.TransformationSolution;
import at.ac.tuwien.big.momot.problem.solution.variable.ITransformationVariable;
import at.ac.tuwien.big.momot.problem.solution.variable.UnitApplicationVariable;
import at.ac.tuwien.big.momot.search.fitness.EGraphMultiDimensionalFitnessFunction;
import at.ac.tuwien.big.momot.search.fitness.IEGraphMultiDimensionalFitnessFunction;
import at.ac.tuwien.big.momot.search.fitness.dimension.AbstractEGraphFitnessDimension;
import at.ac.tuwien.big.momot.search.fitness.dimension.TransformationLengthDimension;
import at.ac.tuwien.big.momot.util.MomotUtil;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.RuleApplication;
import org.eclipse.emf.henshin.model.Parameter;
import org.eclipse.epsilon.emc.emf.AbstractEmfModel;
import org.eclipse.epsilon.emc.emf.EmfMetaModel;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.EolModule;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.moeaframework.algorithm.EpsilonMOEA;
import org.moeaframework.algorithm.NSGAII;
import org.moeaframework.algorithm.RandomSearch;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Population;
import org.moeaframework.util.progress.ProgressListener;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Font;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;
import trafochainselection.TrafochainselectionPackage;
import trafochainselection.Transformation;
import trafochainselection.TransformationModel;

@SuppressWarnings("all")
public class ExhaustiveChainSearch {
   protected static final String INITIAL_MODEL = "problem/Instance-1.xmi";

   protected static final int SOLUTION_LENGTH = 6;

   public static void finalization() {
      System.out.println("Search finished.");
   }

   public static void initialization() {
      TrafochainselectionPackage.eINSTANCE.eClass();
      System.out.println("Search started.");
   }

   public static void main(final String... args) {
      initialization();
      final int nrRuns = 1;
      final ExhaustiveChainSearch search = new ExhaustiveChainSearch();
      search.performSearch(INITIAL_MODEL, SOLUTION_LENGTH);
      List<Double> runtimes = new ArrayList<>();

      for(int i = 0; i < nrRuns + 1; i++) {

         final double runtime = search.performExploration(INITIAL_MODEL, SOLUTION_LENGTH);
         runtimes.add(runtime);
      }

      runtimes = runtimes.subList(1, runtimes.size());
      System.out.println("Exploration times: " + Arrays.toString(runtimes.toArray()));
      System.out.println(String.format("Mean exploration time: %.3f",
            runtimes.stream().mapToDouble(x -> x.doubleValue()).average().getAsDouble()));
      finalization();
   }

   protected final String[] modules = new String[] { "transformations/trafochain.henshin" };

   protected final String[] unitsToRemove = new String[] {};

   protected final int nrRuns = 1;

   protected String baseName;

   protected double significanceLevel = 0.01;

   protected String[] hexCols = { "#800000", "#808000", "#008000", "#800080", "#008080", "#000080", "#FFA500",
         "#A0522D", "#708090", "#BC8F8F" };

   protected IFitnessDimension<TransformationSolution> _createConstraint_0() {
      return new AbstractEGraphFitnessDimension("chainEndsInTargetMM",
            at.ac.tuwien.big.moea.search.fitness.dimension.IFitnessDimension.FunctionType.Minimum) {
         @Override
         protected double internalEvaluate(final TransformationSolution solution) {
            final EGraph graph = solution.execute();
            final EObject root = MomotUtil.getRoot(graph);
            return _createConstraintHelper_0(solution, graph, root);
         }
      };
   }

   protected double _createConstraintHelper_0(final TransformationSolution solution, final EGraph graph,
         final EObject root) {
      return FitnessCalculator.calculateChainValidity((TransformationModel) root);
   }

   protected ProgressListener _createListener_0() {
      final SeedRuntimePrintListener _seedRuntimePrintListener = new SeedRuntimePrintListener();
      return _seedRuntimePrintListener;
   }

   protected IFitnessDimension<TransformationSolution> _createObjective_0() {
      return new AbstractEGraphFitnessDimension("Tr. Coverage",
            at.ac.tuwien.big.moea.search.fitness.dimension.IFitnessDimension.FunctionType.Maximum) {
         @Override
         protected double internalEvaluate(final TransformationSolution solution) {
            final EGraph graph = solution.execute();
            final EObject root = MomotUtil.getRoot(graph);
            return _createObjectiveHelper_0(solution, graph, root);
         }
      };
   }

   protected IFitnessDimension<TransformationSolution> _createObjective_1() {
      return new AbstractEGraphFitnessDimension("Tr. Complexity",
            at.ac.tuwien.big.moea.search.fitness.dimension.IFitnessDimension.FunctionType.Minimum) {
         @Override
         protected double internalEvaluate(final TransformationSolution solution) {
            final EGraph graph = solution.execute();
            final EObject root = MomotUtil.getRoot(graph);
            return _createObjectiveHelper_1(solution, graph, root);
         }
      };
   }

   protected IFitnessDimension<TransformationSolution> _createObjective_2() {
      return new AbstractEGraphFitnessDimension("Model Coverage.",
            at.ac.tuwien.big.moea.search.fitness.dimension.IFitnessDimension.FunctionType.Maximum) {
         @Override
         protected double internalEvaluate(final TransformationSolution solution) {
            final EGraph graph = solution.execute();
            final EObject root = MomotUtil.getRoot(graph);
            return _createObjectiveHelper_2(solution, graph, root);
         }
      };
   }

   protected IFitnessDimension<TransformationSolution> _createObjective_3() {
      return new AbstractEGraphFitnessDimension("MM similarity",
            at.ac.tuwien.big.moea.search.fitness.dimension.IFitnessDimension.FunctionType.Maximum) {
         @Override
         protected double internalEvaluate(final TransformationSolution solution) {
            final EGraph graph = solution.execute();
            final EObject root = MomotUtil.getRoot(graph);
            return _createObjectiveHelper_3(solution, graph, root);
         }
      };
   }

   protected IFitnessDimension<TransformationSolution> _createObjective_4() {
      final IFitnessDimension<TransformationSolution> dimension = _createObjectiveHelper_4();
      dimension.setName("SolutionLength");
      dimension.setFunctionType(at.ac.tuwien.big.moea.search.fitness.dimension.IFitnessDimension.FunctionType.Minimum);
      return dimension;
   }

   protected double _createObjectiveHelper_0(final TransformationSolution solution, final EGraph graph,
         final EObject root) {
      try {
         return FitnessCalculator.calculateTransformationCoverage_new1((TransformationModel) root);
      } catch(final Throwable _e) {
         throw Exceptions.sneakyThrow(_e);
      }
   }

   protected double _createObjectiveHelper_1(final TransformationSolution solution, final EGraph graph,
         final EObject root) {
      try {
         return FitnessCalculator.calculateStructuralFeatures1((TransformationModel) root);
      } catch(final Throwable _e) {
         throw Exceptions.sneakyThrow(_e);
      }
   }

   protected double _createObjectiveHelper_2(final TransformationSolution solution, final EGraph graph,
         final EObject root) {
      try {
         return FitnessCalculator.calculateModelCoverage((TransformationModel) root);
      } catch(final Throwable _e) {
         throw Exceptions.sneakyThrow(_e);
      }
   }

   protected double _createObjectiveHelper_3(final TransformationSolution solution, final EGraph graph,
         final EObject root) {
      try {
         return FitnessCalculator.calculateGraphSimilarityChain((TransformationModel) root);
      } catch(final Throwable _e) {
         throw Exceptions.sneakyThrow(_e);
      }
   }

   protected IFitnessDimension<TransformationSolution> _createObjectiveHelper_4() {
      final TransformationLengthDimension _transformationLengthDimension = new TransformationLengthDimension();
      return _transformationLengthDimension;
   }

   protected IRegisteredAlgorithm<RandomSearch> _createRegisteredAlgorithm_0(
         final TransformationSearchOrchestration orchestration,
         final EvolutionaryAlgorithmFactory<TransformationSolution> moea,
         final LocalSearchAlgorithmFactory<TransformationSolution> local) {
      final IRegisteredAlgorithm<RandomSearch> _createRandomSearch = moea.createRandomSearch();
      return _createRandomSearch;
   }

   protected IRegisteredAlgorithm<NSGAII> _createRegisteredAlgorithm_1(
         final TransformationSearchOrchestration orchestration,
         final EvolutionaryAlgorithmFactory<TransformationSolution> moea,
         final LocalSearchAlgorithmFactory<TransformationSolution> local) {
      final IRegisteredAlgorithm<NSGAII> _createNSGAII = moea.createNSGAII();
      return _createNSGAII;
   }

   protected IRegisteredAlgorithm<NSGAII> _createRegisteredAlgorithm_2(
         final TransformationSearchOrchestration orchestration,
         final EvolutionaryAlgorithmFactory<TransformationSolution> moea,
         final LocalSearchAlgorithmFactory<TransformationSolution> local) {
      final IRegisteredAlgorithm<NSGAII> _createNSGAIII = moea.createNSGAIII();
      return _createNSGAIII;
   }

   protected AbstractRegisteredAlgorithm<EpsilonMOEA> _createRegisteredAlgorithm_3(
         final TransformationSearchOrchestration orchestration,
         final EvolutionaryAlgorithmFactory<TransformationSolution> moea,
         final LocalSearchAlgorithmFactory<TransformationSolution> local) {
      final AbstractRegisteredAlgorithm<EpsilonMOEA> _createEpsilonMOEA = moea.createEpsilonMOEA();
      return _createEpsilonMOEA;
   }

   protected SearchExperiment<TransformationSolution> createExperiment(
         final TransformationSearchOrchestration orchestration) {
      final SearchExperiment<TransformationSolution> experiment = new SearchExperiment<>(orchestration, 1);
      experiment.setNumberOfRuns(nrRuns);
      experiment.addProgressListener(_createListener_0());
      return experiment;
   }

   protected IEGraphMultiDimensionalFitnessFunction createFitnessFunction() {
      final IEGraphMultiDimensionalFitnessFunction function = new EGraphMultiDimensionalFitnessFunction();
      function.addObjective(_createObjective_0());
      function.addObjective(_createObjective_1());
      function.addObjective(_createObjective_2());
      function.addObjective(_createObjective_3());
      function.addObjective(_createObjective_4());
      function.addConstraint(_createConstraint_0());
      return function;
   }

   protected EGraph createInputGraph(final String initialGraph, final ModuleManager moduleManager) {
      final EGraph graph = moduleManager.loadGraph(initialGraph);
      return graph;
   }

   protected ModuleManager createModuleManager() {
      final ModuleManager manager = new ModuleManager();
      for(final String module : modules) {
         manager.addModule(URI.createFileURI(new File(module).getPath().toString()).toString());
      }
      manager.removeUnits(unitsToRemove);
      return manager;
   }

   protected TransformationSearchOrchestration createOrchestration(final String initialGraph, final int solutionLength,
         final ModuleManager moduleManager, final IEGraphMultiDimensionalFitnessFunction fitness) {
      final TransformationSearchOrchestration orchestration = new TransformationSearchOrchestration();
      final EGraph graph = createInputGraph(initialGraph, moduleManager);
      orchestration.setModuleManager(moduleManager);
      orchestration.setProblemGraph(graph);
      orchestration.setSolutionLength(solutionLength);
      orchestration.setFitnessFunction(fitness);

      return orchestration;
   }

   protected TransformationStateExplorer createStateExploration(final String initialGraph,
         final ModuleManager moduleManager, final IEGraphMultiDimensionalFitnessFunction fitness) {
      final TransformationStateExplorer tse = new TransformationStateExplorer(moduleManager.getModules().get(0),
            initialGraph, fitness);
      return tse;
   }

   protected void deriveBaseName(final TransformationSearchOrchestration orchestration) {
      final EObject root = MomotUtil.getRoot(orchestration.getProblemGraph());
      if(root == null || root.eResource() == null || root.eResource().getURI() == null) {
         baseName = getClass().getSimpleName();
      } else {
         baseName = root.eResource().getURI().trimFileExtension().lastSegment();
      }

      // final Graph g = graph("ex1").directed()
      // .with(graph().cluster().nodeAttr().with(Style.FILLED, Color.WHITE).graphAttr()
      // .with(Style.FILLED, Color.LIGHTGREY, Label.of("process #1"))
      // .with(node("a0").link(node("a1").link(node("a2")))),
      // graph("x").cluster().nodeAttr().with(Style.FILLED).graphAttr()
      // .with(Color.BLUE, Label.of("process #2")).with(node("b0").link(node("b1").link(node("b2")))),
      // node("start").with(Shape.M_DIAMOND).link("a0", "b0"),
      // node("a0").with(Style.FILLED, Color.RED.gradient(Color.BLUE)).link("b1"), node("b1").link("a2"),
      // node("a2").link("end"), node("b2").link("end"), node("end").with(Shape.M_SQUARE));
   }

   protected Graph generateGraph(final Population p) {
      Graph g = graph("example 1").directed().graphAttr().with(Rank.dir(LEFT_TO_RIGHT)).nodeAttr()
            .with(Font.name("arial")).linkAttr().with("class", "link-class");
      // .with(graph().cluster().nodeAttr().with(Style.FILLED, Color.WHITE).graphAttr()
      // .with(Style.FILLED, Color.WHITE, Label.of("sample-km3.xmi"))
      // .with(node("\\KM3.ecore").with("margin", "0.2").with(Style.FILLED, Color.rgb("EEEEEE"))))
      // .with(graph().cluster().nodeAttr().with(Style.FILLED, Color.WHITE).graphAttr()
      // .with(Style.FILLED, Color.WHITE, Label.of("out-xml.xmi"))
      // .with(node("\\XML.ecore").with("margin", "0.2").with(Style.FILLED, Color.rgb("EEEEEE"))));
      int i = 0;
      System.setProperty("org.eclipse.emf.ecore.EPackage.Registry.INSTANCE",
            "org.eclipse.emf.ecore.impl.EPackageRegistryImpl");
      for(final TransformationSolution ts : MomotUtil.asIterables(p, TransformationSolution.class)) {
         final EGraph graph = ts.execute();
         final TransformationModel a = (TransformationModel) graph.getRoots().get(0);
         final EList<Transformation> transformations = a.getTransformationchain().getUses();
         final List<Node> nodes = new ArrayList<>();
         String currentInputModel = null;
         String currentCoverage = null;
         int trafoCount = 0;

         try {
            org.apache.commons.io.FileUtils.cleanDirectory(Path.of("models", "tmp").toFile());
         } catch(final IOException e) {
            e.printStackTrace();
         }

         for(final Transformation t : transformations) {
            final double similarityMM = FitnessCalculator.getSimilarityMM().get(t.getId());

            final String sourceMMIdentifier = FitnessCalculator.extractFileNameWithoutExtension(t.getSrc().getId());
            final String targetMMIdentifier = FitnessCalculator.extractFileNameWithoutExtension(t.getTarget().getId());
            final String outputModelPath = "models/tmp/out-" + sourceMMIdentifier + "-to-" + targetMMIdentifier
                  + ".xmi";

            final String trafoName = sourceMMIdentifier + "2" + targetMMIdentifier + ".etl";
            // trafo, inp_model, t.scr.id.name,t.src.id, out_model, t.trg.id.name, t.trg.id

            Transformer.transform(Path.of("transformations", "i1", trafoName),
                  currentInputModel == null ? "models/sample-km3.xmi" : currentInputModel, sourceMMIdentifier,
                  Path.of("metamodels", sourceMMIdentifier + ".ecore").toString(), outputModelPath, targetMMIdentifier,
                  Path.of("metamodels", targetMMIdentifier + ".ecore").toString());

            currentInputModel = outputModelPath;
            final EolModule module = new EolModule();

            try {
               module.parse(new File("model_cov.eol"));

               final OutputStream outputStream = new ByteArrayOutputStream();
               final String sourceMM = Path.of("metamodels", targetMMIdentifier + ".ecore").toString();
               final String sourceModel = outputModelPath;

               AbstractEmfModel emfModelMM = null;

               if(targetMMIdentifier.compareTo("Ecore") != 0) {
                  emfModelMM = new EmfModel();
                  emfModelMM.setName("MM"); // Set the name to "MM"
                  ((EmfModel) emfModelMM).setModelFile(sourceMM);
               } else {
                  EPackage.Registry.INSTANCE.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);

                  emfModelMM = new EmfMetaModel("MM", EcorePackage.eNS_URI);
               }

               final EmfModel emfModelM = new EmfModel();
               emfModelM.setName("m"); // Set the name to "MM"
               emfModelM.setModelFile(sourceModel); // Set the path to the model

               emfModelMM.load();
               emfModelM.load();

               module.getContext().setOutputStream(new PrintStream(outputStream));
               module.getContext().getModelRepository().addModel(emfModelMM);
               module.getContext().getModelRepository().addModel(emfModelM);

               module.execute();

               emfModelMM.dispose();
               emfModelM.dispose();

               currentCoverage = outputStream.toString();
            } catch(final Exception e) {
               e.printStackTrace();
            }
            final Node srcNode = nodes.isEmpty() ? node(t.getSrc().getId().substring(10)).with(
                  Label.html(t.getSrc().getId().substring(11) + "<br/><i>sample-km3.xmi</i>"),
                  Color.rgb("000000").font()) : node(t.getSrc().getId().substring(10)).with(Font.size(15));

            final Node trgNode = trafoCount + 1 == transformations.size() ? node(t.getTarget().getId().substring(10))
                  .with(Label.html(t.getTarget().getId().substring(11) + "<br/><i>out-xml.xmi</i>"),
                        Color.rgb("000000").font())
                  : node(t.getTarget().getId().substring(10));

            nodes.add(srcNode.link(to(trgNode).with(Style.BOLD,
                  Label.of(String.format("T-COV: %.3f\nT-COM: %d\nM-COV: %.3f\nMM-SIM: %.3f", t.getCoverage(),
                        t.getComplexity(), Double.parseDouble(currentCoverage), similarityMM)),
                  Color.rgb(hexCols[i % hexCols.length]))));

            trafoCount++;
         }

         g = g.with(nodes);
         i++;
      }

      return g;
   }

   protected TransformationResultManager handleResults(final SearchExperiment<TransformationSolution> experiment,
         final TransformationStateExplorer exploration) {
      final ISolutionWriter<TransformationSolution> solutionWriter = experiment.getSearchOrchestration()
            .createSolutionWriter();
      final IPopulationWriter<TransformationSolution> populationWriter = experiment.getSearchOrchestration()
            .createPopulationWriter();
      final TransformationResultManager resultManager = new TransformationResultManager(experiment);

      Population population = exploration.getSolutions(true);
      System.out.println("- Save objectives of state exploration to 'output/exploration/objective_values.txt'");
      SearchResultManager.saveObjectives("output/exploration/objective_values.txt", population);
      SearchResultManager.saveSolutions("output/exploration/solutions/", baseName,
            MomotUtil.asIterables(population, TransformationSolution.class), solutionWriter);

      saveGraph(generateGraph(population), "output/exploration/Paretoset_viz.png");
      saveGraph(generateGraph(exploration.getSolutions(false)), "output/exploration/All_solutions_viz.png");

      population = SearchResultManager.createApproximationSet(experiment, (String[]) null);
      System.out.println("- Save objectives of all algorithms to 'output/objectives/objective_values.txt'");
      SearchResultManager.saveObjectives("output/objectives/objective_values.txt", population);
      System.out.println("---------------------------");
      System.out.println("Objectives of all algorithms");
      System.out.println("---------------------------");
      System.out.println(SearchResultManager.printObjectives(population));

      population = SearchResultManager.createApproximationSet(experiment, (String[]) null);
      System.out.println("- Save solutions of all algorithms to 'output/solutions/objective_values.txt'");
      SearchResultManager.savePopulation("output/solutions/objective_values.txt", population, populationWriter);
      System.out.println("- Save solutions of all algorithms to 'output/solutions/objective_values.txt'");
      SearchResultManager.saveSolutions("output/solutions/", baseName,
            MomotUtil.asIterables(population, TransformationSolution.class), solutionWriter);

      // TransformationResultManager.saveModels(".", "test_dir", population);

      final StringBuilder algo_solutions = new StringBuilder();
      for(final Entry<SearchExecutor, List<NondominatedPopulation>> entry : resultManager.getResults().entrySet()) {

         System.out.println(entry.getKey().getName());

         population = SearchResultManager.createApproximationSet(experiment, entry.getKey().getName());
         System.out.println(SearchResultManager.printObjectives(population) + "\n");

         population = SearchResultManager.createApproximationSet(experiment, entry.getKey().getName());
         SearchResultManager.saveObjectives("output/objectives/" + entry.getKey().getName() + ".txt", population);

         algo_solutions.append("----------------------------------------------------------------------\n");
         algo_solutions.append(entry.getKey().getName());
         algo_solutions.append("\n\n----------------------------------------------------------------------");

         int noSolutions = 1;
         for(final TransformationSolution ts : MomotUtil.asIterables(population, TransformationSolution.class)) {
            algo_solutions.append("\nSolution " + noSolutions++ + ":\n");
            for(final ITransformationVariable tv : ts.getVariables()) {
               if(tv instanceof UnitApplicationVariable) {
                  final UnitApplicationVariable uav = (UnitApplicationVariable) tv;
                  algo_solutions.append(uav.getAssignment().toString());
                  for(final RuleApplication ra : uav.getAppliedRules()) {
                     algo_solutions.append(String.format("\tRule '%s'\n", ra.getUnit().getName()));
                     for(final Parameter p : ra.getResultMatch().getUnit().getParameters()) {
                        final Object o = ra.getResultParameterValue(p.getName());
                        algo_solutions
                              .append(String.format("\t\t- parameter '%s' => '%s'\n", p.getName(), o.toString()));
                     }

                  }
               }
            }
            algo_solutions.append("\nObjectives: " + Arrays.toString(ts.getObjectives()));

         }
         algo_solutions.append("\n\n");

      }

      Graphviz.useDefaultEngines();

      // saveGraph(generateGraph(population), "output/graphs/Paretoset_viz.png");

      BufferedWriter writer = null;
      try {
         writer = new BufferedWriter(new FileWriter("output/solutions/units_and_rules.txt", false));
         writer.append(algo_solutions);
         writer.close();
      } catch(final IOException e) {
         e.printStackTrace();
      }

      population = SearchResultManager.createApproximationSet(experiment, (String[]) null);
      System.out.println("- Save models of all algorithms to 'output/models/'");
      TransformationResultManager.saveModels("output/models/", baseName, population);

      return resultManager;
   }

   protected SearchAnalyzer performAnalysis(final SearchExperiment<TransformationSolution> experiment) {
      final SearchAnalysis analysis = new SearchAnalysis(experiment);
      analysis.setHypervolume(true);
      analysis.setShowAggregate(true);
      analysis.setShowIndividualValues(true);
      analysis.setShowStatisticalSignificance(true);
      analysis.setSignificanceLevel(significanceLevel);
      final SearchAnalyzer searchAnalyzer = analysis.analyze();
      System.out.println("---------------------------");
      System.out.println("Analysis Results");
      System.out.println("---------------------------");
      searchAnalyzer.printAnalysis();
      System.out.println("---------------------------");
      try {
         System.out.println("- Save Analysis to 'output/analysis/analysis.txt'");
         searchAnalyzer.saveAnalysis(new File("output/analysis/analysis.txt"));
      } catch(final IOException e) {
         e.printStackTrace();
      }
      System.out.println("- Save Indicator BoxPlots to 'output/analysis/'");
      searchAnalyzer.saveIndicatorBoxPlots("output/analysis/", baseName);
      return searchAnalyzer;
   }

   public double performExploration(final String initialGraph, final int solutionLength) {
      final List<Double> runtimes = new ArrayList<>();

      final ModuleManager moduleManager = createModuleManager();
      final EGraph graph = createInputGraph(initialGraph, moduleManager);
      final IEGraphMultiDimensionalFitnessFunction f = createFitnessFunction();

      TransformationStateExplorer exploration = null;
      exploration = createStateExploration(INITIAL_MODEL, moduleManager, f);
      exploration.setSolutionReprFunction(s -> {

         final TransformationModel a = (TransformationModel) s.getResultGraph().getRoots().get(0);
         final EList<Transformation> transformations = a.getTransformationchain().getUses();
         final List<Node> nodes = new ArrayList<>();
         final StringBuilder sb = new StringBuilder(

               String.format("%s -> %s",
                     transformations.get(0).getSrc().getId().substring(11,
                           transformations.get(0).getSrc().getId().length() - 6),
                     transformations.get(0).getTarget().getId().substring(11,
                           transformations.get(0).getTarget().getId().length() - 6)));
         for(final Transformation t : transformations.subList(1, transformations.size())) {
            sb.append(String.format(" -> %s", t.getTarget().getId().substring(11, t.getTarget().getId().length() - 6)));
         }
         sb.append(String.format(" (%s)", Arrays.toString(s.getObjectives())));
         return sb.toString();
      });
      exploration.run();

      return exploration.getElapsedTime();

   }

   public void performSearch(final String initialGraph, final int solutionLength) {
      final ModuleManager moduleManager = createModuleManager();
      final EGraph graph = createInputGraph(initialGraph, moduleManager);
      final IEGraphMultiDimensionalFitnessFunction f = createFitnessFunction();
      final TransformationSearchOrchestration orchestration = createOrchestration(initialGraph, solutionLength,
            moduleManager, f);
      TransformationStateExplorer exploration = null;
      exploration = createStateExploration(INITIAL_MODEL, moduleManager, f);
      exploration.setSolutionReprFunction(s -> {

         final TransformationModel a = (TransformationModel) s.getResultGraph().getRoots().get(0);
         final EList<Transformation> transformations = a.getTransformationchain().getUses();
         final List<Node> nodes = new ArrayList<>();
         final StringBuilder sb = new StringBuilder(

               String.format("%s -> %s",
                     transformations.get(0).getSrc().getId().substring(11,
                           transformations.get(0).getSrc().getId().length() - 6),
                     transformations.get(0).getTarget().getId().substring(11,
                           transformations.get(0).getTarget().getId().length() - 6)));
         for(final Transformation t : transformations.subList(1, transformations.size())) {
            sb.append(String.format(" -> %s", t.getTarget().getId().substring(11, t.getTarget().getId().length() - 6)));
         }
         sb.append(String.format(" (%s)", Arrays.toString(s.getObjectives())));
         return sb.toString();
      });
      deriveBaseName(orchestration);
      printSearchInfo(orchestration);
      final SearchExperiment<TransformationSolution> experiment = createExperiment(orchestration);
      experiment.run();
      exploration.run();

      System.out.println("-------------------------------------------------------");
      System.out.println("Analysis");
      System.out.println("-------------------------------------------------------");
      performAnalysis(experiment);
      System.out.println("-------------------------------------------------------");
      System.out.println("Results");
      System.out.println("-------------------------------------------------------");
      handleResults(experiment, exploration);
   }

   public void printSearchInfo(final TransformationSearchOrchestration orchestration) {
      System.out.println("-------------------------------------------------------");
      System.out.println("Search");
      System.out.println("-------------------------------------------------------");
      System.out.println("InputModel:      " + INITIAL_MODEL);
      System.out.println("Objectives:      " + orchestration.getFitnessFunction().getObjectiveNames());
      System.out.println("NrObjectives:    " + orchestration.getNumberOfObjectives());
      System.out.println("Constraints:     " + orchestration.getFitnessFunction().getConstraintNames());
      System.out.println("NrConstraints:   " + orchestration.getNumberOfConstraints());
      System.out.println("Transformations: " + Arrays.toString(modules));
      System.out.println("Units:           " + orchestration.getModuleManager().getUnits());
      System.out.println("SolutionLength:  " + orchestration.getSolutionLength());
      System.out.println("AlgorithmRuns:   " + nrRuns);
      System.out.println("---------------------------");
   }

   protected void saveGraph(final Graph g, final String path) {
      try {
         Graphviz.fromGraph(g).render(Format.PNG).toFile(new File(path));
      } catch(final IOException e1) {
         e1.printStackTrace();
      }
   }
}
