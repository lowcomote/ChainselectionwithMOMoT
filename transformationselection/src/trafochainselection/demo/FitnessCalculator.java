package trafochainselection.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.epsilon.etl.EtlModule;
import org.eclipse.epsilon.etl.chain.selection.Chaining_MT;

import trafochainselection.TrafochainselectionPackage;
import trafochainselection.Transformation;
import trafochainselection.TransformationModel;

public final class FitnessCalculator {

   static Path metamodelsRoot = Paths.get("metamodels");
   static Path scriptRoot = Paths.get("scripts");
   static File scriptPath = new File("scripts");

   final static String filePath = "C:\\Users\\sahay\\git\\repository\\org.eclipse.epsilon.etl.chain.optimisation\\write.txt";
   final static String filePath2 = "similarityvalues.txt";
   final static String filePath_complexity = "C:\\Users\\sahay\\git\\repository\\org.eclipse.epsilon.etl.chain.optimisation\\writeComplexity.txt";

   // public static double calculateTransformationCoverage(final TransformationModel model) {
   // double dummyCoverage = 0;
   //
   // for(final Transformation t : model.getTransformationchain().getUses()) {
   // dummyCoverage += t.getSrcMMCoverage() + t.getTrgMMCoverage();
   // }
   //
   // return dummyCoverage;
   // }

   // public static void addMM(final String mmpath) {
   // TrafochainselectionFactory factory = null;
   // factory = TrafochainselectionFactory.eINSTANCE;
   // final ModelTransformationRepository mymodel = factory.createModelTransformationRepository();
   // final Metamodel mm = factory.createMetamodel();
   // mm.setId(mmpath);
   // mymodel.getMetamodels().add(mm);
   // }
   //
   // // MyFactory.eINSTANCE.createMyClass() and then call methods
   // // like setAtt1("val1").
   // public static void addrepo() throws IOException {
   // final String mm = URI
   // .createFileURI(new File(Paths.get("metamodels").resolve("Tree.ecore").toString()).getCanonicalPath())
   // .toString();
   // addMM(mm);
   // // System.out.println("123456789");
   //
   // }

   private static final Map<List<String>, Double> chainToModelCoverage = new HashMap<>();

   public static LinkedHashMap<String, Double> calccov() throws Exception {
      final Chaining_MT chainingMt = new Chaining_MT();
      final LinkedHashMap<String, Double> coveragemt = chainingMt.mt_coverage();
      return coveragemt;
   }

   public static double calculateChainValidity(final TransformationModel model) {

      final int trafoLength = model.getTransformationchain().getUses().size();

      if(trafoLength == 0) {
         return 1;
      }

      final boolean valid = model.getTransformationchain().getFinal()
            .equals(model.getTransformationchain().getUses().get(trafoLength - 1).getTarget());

      if(valid) {
         return 0;
      }

      return 1;
   }

   public static double calculateGraphSimilarityChain(final TransformationModel model) throws Exception {

      // final Chaining_MT chainingMt = new Chaining_MT();

      // final LinkedHashMap<String, Double> mapFromFile = HashMapFromTextFile2();

      double similarity = 1.0;

      for(final Transformation t : model.getTransformationchain().getUses()) {
         double sim = 1;
         // System.out.println(t.getId());
         // final double mtsim = mapFromFile.get(t.getId());
         // System.out.println(mtsim);
         for(final String i : getSimilarityMM().keySet()) {
            if(t.getId().equals(i)) {
               // sim = mtsim;
               sim = getSimilarityMM().get(i);
               similarity *= sim;
            }

            // similarity *= sim;
         }
         // System.out.println("Graph similarity in the transformation " + t.getSrc().getId() + " -> "
         // + t.getTarget().getId() + "= " + sim + "\n");

      }
      // System.out.println("\nTotal graph similarity of chain is " + similarity + "\n");

      return similarity;

   }

   public static double calculateModelCoverage(final TransformationModel model) throws Exception {
      // final Chaining_MT chainingMt = new Chaining_MT();
      double modelcoverage = 0.0;
      final ArrayList<String> chain = new ArrayList<>();
      for(final Transformation t : model.getTransformationchain().getUses()) {
         chain.add(t.getId());
      }

      // if(chainToModelCoverage.containsKey(chain)) {
      // return chainToModelCoverage.get(chain);
      // }
      //
      // // compute chain coverage and store it
      // final double mCov = calculateModelCoverage(chain);
      // chainToModelCoverage.put(chain, mCov);
      //
      // return mCov;

      /*
       * {
       * System.out.println(chain);
       * }
       */
      if(chain.equals(getChain1())) {
         // System.out.println(getChain1());
         modelcoverage = 0.3125;
      } else if(chain.equals(getChain1_1())) {
         // System.out.println(getChain1_1());
         modelcoverage = 0.3125;
      }
      if(chain.equals(getChain1_j1())) {
         // System.out.println(getChain1_j1());
         modelcoverage = 0.3125;
      } else if(chain.equals(getChain1_1_j1())) {
         // System.out.println(getChain1_1_j1());
         modelcoverage = 0.3125;
      } else if(chain.equals(getChain2())) {
         // System.out.println(getChain2());
         modelcoverage = 0.3125;
      } else if(chain.equals(getChain2_j1())) {
         // System.out.println(getChain2_j1());
         modelcoverage = 0.3125;
      } else if(chain.equals(getChain3())) {
         // System.out.println(getChain3());
         modelcoverage = 0.1875;
      } else if(chain.equals(getChain3_j1())) {
         // System.out.println(getChain3_j1());
         modelcoverage = 0.1875;
      } else if(chain.equals(getChain3_t1())) {
         // System.out.println(getChain3_t1());
         modelcoverage = 0.1875;
      } else if(chain.equals(getChain3_j1_t1())) {
         // System.out.println(getChain3_j1_t1());
         modelcoverage = 0.1875;
      } else if(chain.equals(getChain4())) {
         // System.out.println(getChain4());
         modelcoverage = 0.1875;
      } else if(chain.equals(getChain4_1())) {
         // System.out.println(getChain4_1());
         modelcoverage = 0.1875;
      } else if(chain.equals(getChain4_j1())) {
         // System.out.println(getChain4_j1());
         modelcoverage = 0.1875;
      } else if(chain.equals(getChain4_1_j1())) {
         // System.out.println(getChain4_1_j1());
         modelcoverage = 0.1875;
      } else if(chain.equals(getChain4_t1())) {
         // System.out.println(getChain4_t1());
         modelcoverage = 0.1875;
      } else if(chain.equals(getChain4_1_t1())) {
         // System.out.println(getChain4_1_t1());
         modelcoverage = 0.1875;
      } else if(chain.equals(getChain4_j1_t1())) {
         // System.out.println(getChain4_j1_t1());
         modelcoverage = 0.1875;
      } else if(chain.equals(getChain4_1_j1_t1())) {
         System.out.println(getChain4_1_j1_t1());
         modelcoverage = 0.1875;
      } else if(chain.equals(getChain5())) {
         System.out.println(getChain5());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain5_1())) {
         System.out.println(getChain5_1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain5_j1())) {
         System.out.println(getChain5_j1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain5_1_j1())) {
         System.out.println(getChain5_1_j1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain5_t1())) {
         System.out.println(getChain5_t1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain5_1_t1())) {
         System.out.println(getChain5_1_t1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain5_j1_t1())) {
         System.out.println(getChain5_j1_t1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain5_1_j1_t1())) {
         System.out.println(getChain5_1_j1_t1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain5_h1())) {
         System.out.println(getChain5_h1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain5_1_h1())) {
         System.out.println(getChain5_1_h1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain5_j1_h1())) {
         System.out.println(getChain5_j1_h1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain5_1_j1_h1())) {
         System.out.println(getChain5_1_j1_h1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain5_t1_h1())) {
         System.out.println(getChain5_t1_h1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain5_1_t1_h1())) {
         System.out.println(getChain5_1_t1_h1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain5_j1_t1_h1())) {
         System.out.println(getChain5_j1_t1_h1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain5_1_j1_t1_h1())) {
         System.out.println(getChain5_1_j1_t1_h1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain6())) {
         System.out.println(getChain6());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain6_j1())) {
         System.out.println(getChain6_j1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain6_t1())) {
         System.out.println(getChain6_t1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain6_j1_t1())) {
         System.out.println(getChain6_j1_t1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain6_h1())) {
         System.out.println(getChain6_h1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain6_j1_h1())) {
         System.out.println(getChain6_j1_h1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain6_t1_h1())) {
         System.out.println(getChain6_t1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain6_j1_t1_h1())) {
         System.out.println(getChain6_j1_t1_h1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain7())) {
         System.out.println(getChain7());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain8())) {
         System.out.println(getChain8());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain8_1())) {
         System.out.println(getChain8_1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain8_j1())) {
         System.out.println(getChain8_j1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain8_1_j1())) {
         System.out.println(getChain8_1_j1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain8_h1())) {
         System.out.println(getChain8_h1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain8_1_h1())) {
         System.out.println(getChain8_1_h1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain8_j1_h1())) {
         System.out.println(getChain8_j1_h1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain8_1_j1_h1())) {
         System.out.println(getChain8_1_j1_h1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain9())) {
         System.out.println(getChain9());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain9_j1())) {
         System.out.println(getChain9_j1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain9_h1())) {
         System.out.println(getChain9_h1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain9_j1_h1())) {
         System.out.println(getChain9_j1_h1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain10())) {
         System.out.println(getChain10());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain10_1())) {
         System.out.println(getChain10_1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain10_t1())) {
         System.out.println(getChain10_t1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain10_1_t1())) {
         System.out.println(getChain10_1_t1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain10_h1())) {
         System.out.println(getChain10_h1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain10_1_h1())) {
         System.out.println(getChain10_1_h1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain10_t1_h1())) {
         System.out.println(getChain10_t1_h1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain10_1_t1_h1())) {
         System.out.println(getChain10_1_t1_h1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain11())) {
         System.out.println(getChain11());
         modelcoverage = 0.1875;
      } else if(chain.equals(getChain11_1())) {
         System.out.println(getChain11_1());
         modelcoverage = 0.1875;
      } else if(chain.equals(getChain11_t1())) {
         System.out.println(getChain11_t1());
         modelcoverage = 0.1875;
      } else if(chain.equals(getChain11_1_t1())) {
         System.out.println(getChain11_1_t1());
         modelcoverage = 0.1875;
      } else if(chain.equals(getChain12())) {
         System.out.println(getChain12());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain12_t1())) {
         System.out.println(getChain12_t1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain12_h1())) {
         System.out.println(getChain12_h1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain12_t1_h1())) {
         System.out.println(getChain12_t1_h1());
         modelcoverage = 0.4375;
      } else if(chain.equals(getChain13())) {
         System.out.println(getChain13());
         modelcoverage = 0.1875;
      } else if(chain.equals(getChain13_t1())) {
         System.out.println(getChain13_t1());
         modelcoverage = 0.1875;
      } else {
         modelcoverage = 0;
      }

      // if(count == 1) {
      // modelcoverage = 0.4375;
      // }
      // if(count == 2) {
      // modelcoverage = 0.3125;
      // }
      // if(count == 3) {
      // modelcoverage = 0.3125;
      // }
      // if(count == 4) {
      // modelcoverage = 0.4375;
      // }
      // if(count == 5) {
      // modelcoverage = 0.4375;
      // }

      // System.out.println("Model Coverage: " + modelcoverage);
      return modelcoverage;
   }

   public static int calculateStructuralFeatures(final TransformationModel model) throws Exception {

      final Chaining_MT chainingMt = new Chaining_MT();

      final int min = 99999;
      // double coverage_chain = 1;
      int total = 0;
      final int min_str_feature = 0;

      int sum = 0;
      int min_feature = 9999;
      for(final Transformation element : model.getTransformationchain().getUses()) {

         for(final String ele : chainingMt.identifyETL(element.getSrc().getId(), element.getTarget().getId())) {

            final EtlModule module1 = new EtlModule();

            module1.parse(scriptRoot.resolve(ele));

            total = chainingMt.calculateMTChain1(module1);
            if(total < min_feature) {
               min_feature = total;
            }
            sum = sum + min_feature;
            // sum.add(total);
            // sum[t] = sum[t] + total;
            // System.out.println("Total expressions/operators used in the transformation " + element.getSrc().getId()
            // + " -> " + element.getTarget().getId() + "= " + min_feature + "\n");
         }

      }

      // min_str_feature = Collections.min(sum);
      // System.out.println("Minimum structural features of the chain: " + sum);

      return sum;

   }

   // use min of calculateStructuralFeatures as an objective or use chain to use EtlModule
   public static int calculateStructuralFeatures1(final TransformationModel model) throws Exception {

      int sum = 0;

      for(final Transformation element : model.getTransformationchain().getUses()) {
         int min_feature = 9999;

         // System.out.println(element);
         // final int total = mapFromFile.get(ele);
         final int total = element.getComplexity();
         if(total < min_feature) {
            min_feature = total;
         }

         sum = sum + min_feature;

         // System.out.println("Total expressions/operators used in the transformation " + element.getSrc().getId()
         // + " -> " + element.getTarget().getId() + "= " + min_feature + "\n");
         //
      }

      // System.out.println("Minimum structural features of the chain: " + sum);
      return sum;

   }

   public static int calculateStructuralFeatures2(final TransformationModel model) throws Exception {

      final Chaining_MT chainingMt = new Chaining_MT();

      final int min = 99999;
      // double coverage_chain = 1;
      int total = 0;
      final int min_str_feature = 0;
      final double start1 = System.currentTimeMillis();
      int sum = 0;
      int min_feature = 9999;
      final HashMap<String, Integer> complexitymt = chainingMt.mt_complexity();
      for(final Transformation element : model.getTransformationchain().getUses()) {

         for(final String ele : chainingMt.identifyETL(element.getSrc().getId(), element.getTarget().getId())) {

            // System.out.println(ele);
            // final EtlModule module1 = new EtlModule();
            //
            // module1.parse(scriptRoot.resolve(ele));

            // total = chainingMt.calculateMTChain1(module1);
            // total = complexitymt.get(ele);
            total = element.getComplexity();
            if(total < min_feature) {
               min_feature = total;
            }
            sum = sum + min_feature;
            // sum.add(total);
            // sum[t] = sum[t] + total;
            System.out.println("Total expressions/operators used in the transformation " + element.getSrc().getId()
                  + " -> " + element.getTarget().getId() + "= " + min_feature + "\n");
         }

      }

      // min_str_feature = Collections.min(sum);
      // System.out.println("Minimum structural features of the chain: " + sum);
      // System.out.println("Time taken for complexity in chain " + " is " + (System.currentTimeMillis() - start1) /
      // 1000
      // + " seconds.");
      return sum;

   }

   public static double calculateTransformationCoverage(final TransformationModel model) throws Exception {

      final Chaining_MT chainingMt = new Chaining_MT();

      double coverage_chain = 1;

      for(final Transformation t : model.getModelTransformationRepository().getTransformations()) {

         // coverage_chain = 1;
         // for(final Metamodel element : t.getTarget()) {
         // final ArrayList<Double> max_cov_mt = new ArrayList<>();
         double max = 0;
         for(final Double element : chainingMt.calculateMTCoverage(t.getSrc().getId(), t.getTarget().getId())) {

            // }
            // coverage_chain *= element;
            if(element > max) {
               // max_cov_mt.add(element);
               max = element;
            }

            // System.out.println("\n" + "Individual Coverage of a MT " + t.getSrc().getId() + "2" +
            // t.getTarget().getId()
            // + " is " + element);

         }
         // for(final double m : max_cov_mt) {
         // coverage_chain *= m;
         //
         // }
         coverage_chain *= max;
         // System.out.println(
         // "\n" + "Maximum coverage of a MT " + t.getSrc().getId() + "2" + t.getTarget().getId() + " is " + max);
         // }
      }

      // System.out.println("\nTotal coverage of chain " + model.getTransformationchain().getStart().getId() + " -> "
      // + model.getTransformationchain().getFinal().getId() + " is " + coverage_chain + "\n");
      // System.out.println("Maximum coverage of a chain is " + max_cov_mt + "\n");

      return coverage_chain;

   }

   public static double calculateTransformationCoverage_new(final TransformationModel model) throws Exception {

      final Chaining_MT chainingMt = new Chaining_MT();

      double coverage_chain = 1;

      for(final Transformation t : model.getTransformationchain().getUses()) {

         final double mtcoverage = chainingMt.calculateMTCoverage_new(t.getSrc().getId(), t.getTarget().getId());
         // System.out.println("\n" + "Maximum coverage of a MT " + t.getSrc().getId() + " -> " + t.getTarget().getId()
         // + " is " + mtcoverage);
         coverage_chain *= mtcoverage;

      }
      // System.out.println("\nTotal coverage of chain " + model.getTransformationchain().getStart().getId() + " -> "
      // + model.getTransformationchain().getFinal().getId() + " is " + coverage_chain + "\n");

      return coverage_chain;

   }

   public static double calculateTransformationCoverage_new1(final TransformationModel model) throws Exception {

      final Chaining_MT chainingMt = new Chaining_MT();

      double coverage_chain = 1;
      for(final Transformation t : model.getTransformationchain().getUses()) {
         double max_feature = 0.0;

         final double mtcoverage = t.getCoverage();
         if(mtcoverage > max_feature) {
            max_feature = mtcoverage;
         }

         coverage_chain *= max_feature;

      }

      return coverage_chain;

   }

   // public static double calculateTransformationCoverage_Optimized(final TransformationModel model) throws Exception {
   // final Chaining_MT chainingMt = new Chaining_MT();
   // // final EtlChainOptimiser eco = new EtlChainOptimiser();
   // // EtlChainOptimiser.calculateTransformationCoverageOnOptimizedTransformation_opt();
   // double coverage_chain = 1;
   //
   // for(final Transformation t : model.getTransformationchain().getUses()) {
   //
   // // for(final Metamodel element : t.getTarget()) {
   // double max_cov_mt = 0;
   // for(final Double element : chainingMt.calculateMTCoverage_opt(t.getSrc().getId(), t.getTarget().getId())) {
   //
   // // }
   // // coverage_chain *= element;
   // if(element > max_cov_mt) {
   // max_cov_mt = element;
   // }
   // System.out.println("\n" + "Individual Coverage of a MT " + t.getSrc().getId() + " -> "
   // + t.getTarget().getId() + " is " + element);
   //
   // }
   //
   // System.out.println("\n" + "Maximum coverage of a MT " + t.getSrc().getId() + " -> " + t.getTarget().getId()
   // + " is " + max_cov_mt);
   // coverage_chain *= max_cov_mt;
   //
   // // }
   // }
   // System.out.println("\nTotal coverage of chain is " + coverage_chain + "\n");
   // // System.out.println("Maximum coverage of a chain is " + max_cov_mt + "\n");
   //
   // return coverage_chain;
   // }

   // public static double calculateTransformationCoverage_Optimized2(final TransformationModel model) throws Exception
   // {
   //
   // EtlChainOptimiser.calculateTransformationCoverageOnOptimizedTransformation();
   //
   // final double cov = EtlRewritingHandler.calculateTransformationCoverage_Optimized();
   //
   // return cov;
   // }

   public static double calculateTransformationCoverage_new2(final TransformationModel model) throws Exception {

      final Chaining_MT chainingMt = new Chaining_MT();
      // final LinkedHashMap<String, Double> coveragemt = EtlChainOptimiser.mt_coverage();
      final LinkedHashMap<String, Double> mapFromFile = HashMapFromTextFile();
      // System.out.println(mapFromFile);
      double coverage_chain = 1;
      final double start1 = System.currentTimeMillis();

      for(final Transformation t : model.getTransformationchain().getUses()) {
         double max_feature = 0.0;
         for(final String ele : chainingMt.identifyETL(t.getSrc().getId(), t.getTarget().getId())) {
            // final double mtcoverage = chainingMt.calculateMTCoverage_new1(t.getSrc().getId(), t.getTarget().getId());
            // final double mtcoverage = mapFromFile.get(ele);
            final double mtcoverage = t.getCoverage();
            if(mtcoverage > max_feature) {
               max_feature = mtcoverage;
            }
            // System.out.println("\n" + "Maximum coverage of a MT " + t.getSrc().getId() + " -> " +
            // t.getTarget().getId()
            // + " is " + max_feature);
            coverage_chain *= max_feature;
         }

      }
      // System.out.println("\nTotal coverage of chain " + model.getTransformationchain().getStart().getId() + " -> "
      // + model.getTransformationchain().getFinal().getId() + " is " + coverage_chain + "\n");
      // System.out.println("Time taken for coverage in chain " + model.getTransformationchain().getStart().getId()
      // + " -> " + model.getTransformationchain().getFinal().getId() + " is "
      // + (System.currentTimeMillis() - start1) / 1000 + " seconds.");

      return coverage_chain;

   }

   public static void evaluateModel(final String model) throws Exception {
      registerPackage();
      evaluateModel(loadModel(model));
   }

   public static void evaluateModel(final TransformationModel model) throws Exception {
      if(model == null) {
         System.err.println("No correct model loaded... abort.");
         return;
      }

      printGeneralInfo(model);
      // printCorrectnessInfo(model);
      printOptimalityInfo(model);
   }

   public static ArrayList<String> getChain1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource");
      chain1.add("JavaSource2XML");
      return chain1;
   }

   public static ArrayList<String> getChain1_1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource");
      chain1.add("JavaSource2XML");
      return chain1;
   }

   public static ArrayList<String> getChain1_1_j1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource1");
      chain1.add("JavaSource12XML");
      return chain1;
   }

   public static ArrayList<String> getChain1_1_j1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource12");
      chain1.add("JavaSource122XML");
      return chain1;
   }

   public static ArrayList<String> getChain1_1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource2");
      chain1.add("JavaSource22XML");
      return chain1;
   }

   public static ArrayList<String> getChain1_j1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource1");
      chain1.add("JavaSource12XML");
      return chain1;
   }

   public static ArrayList<String> getChain1_j1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource12");
      chain1.add("JavaSource122XML");
      return chain1;
   }

   public static ArrayList<String> getChain1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource2");
      chain1.add("JavaSource22XML");
      return chain1;
   }

   public static ArrayList<String> getChain10() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2Table");
      chain1.add("Table2HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain10_1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12Table");
      chain1.add("Table2HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain10_1_h1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12Table");
      chain1.add("Table2HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain10_1_t1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12Table1");
      chain1.add("Table12HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain10_1_t1_h1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12Table1");
      chain1.add("Table12HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain10_h1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2Table");
      chain1.add("Table2HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain10_t1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2Table1");
      chain1.add("Table12HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain10_t1_h1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2Table1");
      chain1.add("Table12HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain11() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2Table");
      chain1.add("Table2XML");
      return chain1;
   }

   public static ArrayList<String> getChain11_1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12Table");
      chain1.add("Table2XML");
      return chain1;
   }

   public static ArrayList<String> getChain11_1_t1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12Table1");
      chain1.add("Table12XML");
      return chain1;
   }

   public static ArrayList<String> getChain11_t1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2Table1");
      chain1.add("Table12XML");
      return chain1;
   }

   public static ArrayList<String> getChain12() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Table");
      chain1.add("Table2HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain12_h1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Table");
      chain1.add("Table2HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain12_t1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Table1");
      chain1.add("Table12HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain12_t1_h1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Table1");
      chain1.add("Table12HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain13() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Table");
      chain1.add("Table2XML");
      return chain1;
   }

   public static ArrayList<String> getChain13_t1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Table1");
      chain1.add("Table12XML");
      return chain1;
   }

   public static ArrayList<String> getChain2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource");
      chain1.add("JavaSource2XML");
      return chain1;
   }

   public static ArrayList<String> getChain2_j1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource1");
      chain1.add("JavaSource12XML");
      return chain1;
   }

   public static ArrayList<String> getChain2_j1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource12");
      chain1.add("JavaSource122XML");
      return chain1;
   }

   public static ArrayList<String> getChain2_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource2");
      chain1.add("JavaSource22XML");
      return chain1;
   }

   public static ArrayList<String> getChain3() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource");
      chain1.add("JavaSource2Table");
      chain1.add("Table2XML");
      return chain1;
   }

   public static ArrayList<String> getChain3_j1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource1");
      chain1.add("JavaSource12Table");
      chain1.add("Table2XML");
      return chain1;
   }

   public static ArrayList<String> getChain3_j1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource12");
      chain1.add("JavaSource122Table");
      chain1.add("Table2XML");
      return chain1;
   }

   public static ArrayList<String> getChain3_j1_t1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource1");
      chain1.add("JavaSource12Table1");
      chain1.add("Table12XML");
      return chain1;
   }

   public static ArrayList<String> getChain3_j1_t1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource12");
      chain1.add("JavaSource122Table1");
      chain1.add("Table12XML");
      return chain1;
   }

   public static ArrayList<String> getChain3_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource2");
      chain1.add("JavaSource22Table");
      chain1.add("Table2XML");
      return chain1;
   }

   public static ArrayList<String> getChain3_t1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource");
      chain1.add("JavaSource2Table1");
      chain1.add("Table12XML");
      return chain1;
   }

   public static ArrayList<String> getChain3_t1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource2");
      chain1.add("JavaSource22Table1");
      chain1.add("Table12XML");
      return chain1;
   }

   public static ArrayList<String> getChain4() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource");
      chain1.add("JavaSource2Table");
      chain1.add("Table2XML");
      return chain1;
   }

   public static ArrayList<String> getChain4_1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource");
      chain1.add("JavaSource2Table");
      chain1.add("Table2XML");
      return chain1;
   }

   public static ArrayList<String> getChain4_1_j1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource1");
      chain1.add("JavaSource12Table");
      chain1.add("Table2XML");
      return chain1;
   }

   public static ArrayList<String> getChain4_1_j1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource12");
      chain1.add("JavaSource122Table");
      chain1.add("Table2XML");
      return chain1;
   }

   public static ArrayList<String> getChain4_1_j1_t1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource1");
      chain1.add("JavaSource12Table1");
      chain1.add("Table12XML");
      return chain1;
   }

   public static ArrayList<String> getChain4_1_j1_t1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource12");
      chain1.add("JavaSource122Table1");
      chain1.add("Table12XML");
      return chain1;
   }

   public static ArrayList<String> getChain4_1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource2");
      chain1.add("JavaSource22Table");
      chain1.add("Table2XML");
      return chain1;
   }

   public static ArrayList<String> getChain4_1_t1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource");
      chain1.add("JavaSource2Table1");
      chain1.add("Table12XML");
      return chain1;
   }

   public static ArrayList<String> getChain4_1_t1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource2");
      chain1.add("JavaSource22Table1");
      chain1.add("Table12XML");
      return chain1;
   }

   public static ArrayList<String> getChain4_j1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource1");
      chain1.add("JavaSource12Table");
      chain1.add("Table2XML");
      return chain1;
   }

   public static ArrayList<String> getChain4_j1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource12");
      chain1.add("JavaSource122Table");
      chain1.add("Table2XML");
      return chain1;
   }

   public static ArrayList<String> getChain4_j1_t1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource1");
      chain1.add("JavaSource12Table1");
      chain1.add("Table12XML");
      return chain1;
   }

   public static ArrayList<String> getChain4_j1_t1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource12");
      chain1.add("JavaSource122Table1");
      chain1.add("Table12XML");
      return chain1;
   }

   public static ArrayList<String> getChain4_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource2");
      chain1.add("JavaSource22Table");
      chain1.add("Table2XML");
      return chain1;
   }

   public static ArrayList<String> getChain4_t1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource");
      chain1.add("JavaSource2Table1");
      chain1.add("Table12XML");
      return chain1;
   }

   public static ArrayList<String> getChain4_t1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource2");
      chain1.add("JavaSource22Table1");
      chain1.add("Table12XML");
      return chain1;
   }

   public static ArrayList<String> getChain5() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource");
      chain1.add("JavaSource2Table");
      chain1.add("Table2HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource");
      chain1.add("JavaSource2Table");
      chain1.add("Table2HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_1_h1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource");
      chain1.add("JavaSource2Table");
      chain1.add("Table2HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_1_h1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource2");
      chain1.add("JavaSource22Table");
      chain1.add("Table2HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_1_j1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource1");
      chain1.add("JavaSource12Table");
      chain1.add("Table2HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_1_j1_h1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource1");
      chain1.add("JavaSource12Table");
      chain1.add("Table2HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_1_j1_h1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource12");
      chain1.add("JavaSource122Table");
      chain1.add("Table2HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_1_j1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource12");
      chain1.add("JavaSource122Table");
      chain1.add("Table2HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_1_j1_t1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource1");
      chain1.add("JavaSource12Table1");
      chain1.add("Table12HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_1_j1_t1_h1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource1");
      chain1.add("JavaSource12Table1");
      chain1.add("Table12HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_1_j1_t1_h1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource12");
      chain1.add("JavaSource122Table1");
      chain1.add("Table12HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_1_j1_t1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource12");
      chain1.add("JavaSource122Table1");
      chain1.add("Table12HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource2");
      chain1.add("JavaSource22Table");
      chain1.add("Table2HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_1_t1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource");
      chain1.add("JavaSource2Table1");
      chain1.add("Table12HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_1_t1_h1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource");
      chain1.add("JavaSource2Table1");
      chain1.add("Table12HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_1_t1_h1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource2");
      chain1.add("JavaSource22Table1");
      chain1.add("Table12HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_1_t1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource2");
      chain1.add("JavaSource22Table1");
      chain1.add("Table12HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_h1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource");
      chain1.add("JavaSource2Table");
      chain1.add("Table2HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_h1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource");
      chain1.add("JavaSource2Table");
      chain1.add("Table2HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_j1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource1");
      chain1.add("JavaSource12Table");
      chain1.add("Table2HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_j1_h1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource1");
      chain1.add("JavaSource12Table");
      chain1.add("Table2HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_j1_h1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource12");
      chain1.add("JavaSource122Table");
      chain1.add("Table2HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_j1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource12");
      chain1.add("JavaSource122Table");
      chain1.add("Table2HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_j1_t1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource1");
      chain1.add("JavaSource12Table1");
      chain1.add("Table12HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_j1_t1_h1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource1");
      chain1.add("JavaSource12Table1");
      chain1.add("Table12HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_j1_t1_h1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource12");
      chain1.add("JavaSource122Table1");
      chain1.add("Table12HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_j1_t1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource12");
      chain1.add("JavaSource122Table1");
      chain1.add("Table12HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource2");
      chain1.add("JavaSource22Table");
      chain1.add("Table2HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_t1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource");
      chain1.add("JavaSource2Table1");
      chain1.add("Table12HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_t1_h1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource");
      chain1.add("JavaSource2Table1");
      chain1.add("Table12HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_t1_h1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource");
      chain1.add("JavaSource2Table1");
      chain1.add("Table12HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain5_t1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource2");
      chain1.add("JavaSource22Table1");
      chain1.add("Table12HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain6() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource");
      chain1.add("JavaSource2Table");
      chain1.add("Table2HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain6_h1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource");
      chain1.add("JavaSource2Table");
      chain1.add("Table2HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain6_h1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource2");
      chain1.add("JavaSource22Table");
      chain1.add("Table2HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain6_j1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource1");
      chain1.add("JavaSource12Table");
      chain1.add("Table2HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain6_j1_h1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource1");
      chain1.add("JavaSource12Table");
      chain1.add("Table2HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain6_j1_h1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource12");
      chain1.add("JavaSource122Table");
      chain1.add("Table2HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain6_j1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource12");
      chain1.add("JavaSource122Table");
      chain1.add("Table2HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain6_j1_t1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource1");
      chain1.add("JavaSource12Table1");
      chain1.add("Table12HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain6_j1_t1_h1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource1");
      chain1.add("JavaSource12Table1");
      chain1.add("Table12HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain6_j1_t1_h1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource12");
      chain1.add("JavaSource122Table1");
      chain1.add("Table12HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain6_j1_t1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource12");
      chain1.add("JavaSource122Table1");
      chain1.add("Table12HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain6_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource2");
      chain1.add("JavaSource22Table");
      chain1.add("Table2HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain6_t1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource");
      chain1.add("JavaSource2Table1");
      chain1.add("Table12HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain6_t1_h1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource");
      chain1.add("JavaSource2Table1");
      chain1.add("Table12HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain6_t1_h1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource2");
      chain1.add("JavaSource22Table1");
      chain1.add("Table12HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain6_t1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource2");
      chain1.add("JavaSource22Table1");
      chain1.add("Table12HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain7() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32XML");
      return chain1;
   }

   public static ArrayList<String> getChain8() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource");
      chain1.add("JavaSource2HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain8_1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource");
      chain1.add("JavaSource2HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain8_1_h1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource");
      chain1.add("JavaSource2HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain8_1_h1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource2");
      chain1.add("JavaSource22HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain8_1_j1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource1");
      chain1.add("JavaSource12HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain8_1_j1_h1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource1");
      chain1.add("JavaSource12HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain8_1_j1_h1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource12");
      chain1.add("JavaSource122HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain8_1_j1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource12");
      chain1.add("JavaSource122HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain8_1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore1");
      chain1.add("Ecore12JavaSource2");
      chain1.add("JavaSource22HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain8_h1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource");
      chain1.add("JavaSource2HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain8_h1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource2");
      chain1.add("JavaSource22HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain8_j1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource1");
      chain1.add("JavaSource12HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain8_j1_h1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource1");
      chain1.add("JavaSource12HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain8_j1_h1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource12");
      chain1.add("JavaSource122HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain8_j1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource12");
      chain1.add("JavaSource122HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain8_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32Ecore");
      chain1.add("Ecore2JavaSource2");
      chain1.add("JavaSource22HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain9() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource");
      chain1.add("JavaSource2HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain9_h1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource");
      chain1.add("JavaSource2HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain9_h1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource2");
      chain1.add("JavaSource22HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain9_j1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource1");
      chain1.add("JavaSource12HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain9_j1_h1() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource1");
      chain1.add("JavaSource12HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain9_j1_h1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource12");
      chain1.add("JavaSource122HTML1");
      chain1.add("HTML12XML");
      return chain1;
   }

   public static ArrayList<String> getChain9_j1_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource12");
      chain1.add("JavaSource122HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static ArrayList<String> getChain9_j2() {
      final ArrayList<String> chain1 = new ArrayList<>();
      chain1.add("KM32JavaSource2");
      chain1.add("JavaSource22HTML");
      chain1.add("HTML2XML");
      return chain1;
   }

   public static double getModelCoverage(final List<String> tansformationList) {
      if(tansformationList.equals(getChain1())) {
         // System.out.println(getChain1());
         return 0.3125;
      } else if(tansformationList.equals(getChain1_1())) {
         // System.out.println(getChain1_1());
         return 0.3125;
      }
      if(tansformationList.equals(getChain1_j1())) {
         // System.out.println(getChain1_j1());
         return 0.3125;
      } else if(tansformationList.equals(getChain1_1_j1())) {
         // System.out.println(getChain1_1_j1());
         return 0.3125;
      } else if(tansformationList.equals(getChain2())) {
         // System.out.println(getChain2());
         return 0.3125;
      } else if(tansformationList.equals(getChain2_j1())) {
         // System.out.println(getChain2_j1());
         return 0.3125;
      } else if(tansformationList.equals(getChain3())) {
         // System.out.println(getChain3());
         return 0.1875;
      } else if(tansformationList.equals(getChain3_j1())) {
         // System.out.println(getChain3_j1());
         return 0.1875;
      } else if(tansformationList.equals(getChain3_t1())) {
         // System.out.println(getChain3_t1());
         return 0.1875;
      } else if(tansformationList.equals(getChain3_j1_t1())) {
         // System.out.println(getChain3_j1_t1());
         return 0.1875;
      } else if(tansformationList.equals(getChain4())) {
         // System.out.println(getChain4());
         return 0.1875;
      } else if(tansformationList.equals(getChain4_1())) {
         // System.out.println(getChain4_1());
         return 0.1875;
      } else if(tansformationList.equals(getChain4_j1())) {
         // System.out.println(getChain4_j1());
         return 0.1875;
      } else if(tansformationList.equals(getChain4_1_j1())) {
         // System.out.println(getChain4_1_j1());
         return 0.1875;
      } else if(tansformationList.equals(getChain4_t1())) {
         // System.out.println(getChain4_t1());
         return 0.1875;
      } else if(tansformationList.equals(getChain4_1_t1())) {
         // System.out.println(getChain4_1_t1());
         return 0.1875;
      } else if(tansformationList.equals(getChain4_j1_t1())) {
         // System.out.println(getChain4_j1_t1());
         return 0.1875;
      } else if(tansformationList.equals(getChain4_1_j1_t1())) {
         System.out.println(getChain4_1_j1_t1());
         return 0.1875;
      } else if(tansformationList.equals(getChain5())) {
         System.out.println(getChain5());
         return 0.4375;
      } else if(tansformationList.equals(getChain5_1())) {
         System.out.println(getChain5_1());
         return 0.4375;
      } else if(tansformationList.equals(getChain5_j1())) {
         System.out.println(getChain5_j1());
         return 0.4375;
      } else if(tansformationList.equals(getChain5_1_j1())) {
         System.out.println(getChain5_1_j1());
         return 0.4375;
      } else if(tansformationList.equals(getChain5_t1())) {
         System.out.println(getChain5_t1());
         return 0.4375;
      } else if(tansformationList.equals(getChain5_1_t1())) {
         System.out.println(getChain5_1_t1());
         return 0.4375;
      } else if(tansformationList.equals(getChain5_j1_t1())) {
         System.out.println(getChain5_j1_t1());
         return 0.4375;
      } else if(tansformationList.equals(getChain5_1_j1_t1())) {
         System.out.println(getChain5_1_j1_t1());
         return 0.4375;
      } else if(tansformationList.equals(getChain5_h1())) {
         System.out.println(getChain5_h1());
         return 0.4375;
      } else if(tansformationList.equals(getChain5_1_h1())) {
         System.out.println(getChain5_1_h1());
         return 0.4375;
      } else if(tansformationList.equals(getChain5_j1_h1())) {
         System.out.println(getChain5_j1_h1());
         return 0.4375;
      } else if(tansformationList.equals(getChain5_1_j1_h1())) {
         System.out.println(getChain5_1_j1_h1());
         return 0.4375;
      } else if(tansformationList.equals(getChain5_t1_h1())) {
         System.out.println(getChain5_t1_h1());
         return 0.4375;
      } else if(tansformationList.equals(getChain5_1_t1_h1())) {
         System.out.println(getChain5_1_t1_h1());
         return 0.4375;
      } else if(tansformationList.equals(getChain5_j1_t1_h1())) {
         System.out.println(getChain5_j1_t1_h1());
         return 0.4375;
      } else if(tansformationList.equals(getChain5_1_j1_t1_h1())) {
         System.out.println(getChain5_1_j1_t1_h1());
         return 0.4375;
      } else if(tansformationList.equals(getChain6())) {
         System.out.println(getChain6());
         return 0.4375;
      } else if(tansformationList.equals(getChain6_j1())) {
         System.out.println(getChain6_j1());
         return 0.4375;
      } else if(tansformationList.equals(getChain6_t1())) {
         System.out.println(getChain6_t1());
         return 0.4375;
      } else if(tansformationList.equals(getChain6_j1_t1())) {
         System.out.println(getChain6_j1_t1());
         return 0.4375;
      } else if(tansformationList.equals(getChain6_h1())) {
         System.out.println(getChain6_h1());
         return 0.4375;
      } else if(tansformationList.equals(getChain6_j1_h1())) {
         System.out.println(getChain6_j1_h1());
         return 0.4375;
      } else if(tansformationList.equals(getChain6_t1_h1())) {
         System.out.println(getChain6_t1());
         return 0.4375;
      } else if(tansformationList.equals(getChain6_j1_t1_h1())) {
         System.out.println(getChain6_j1_t1_h1());
         return 0.4375;
      } else if(tansformationList.equals(getChain7())) {
         System.out.println(getChain7());
         return 0.4375;
      } else if(tansformationList.equals(getChain8())) {
         System.out.println(getChain8());
         return 0.4375;
      } else if(tansformationList.equals(getChain8_1())) {
         System.out.println(getChain8_1());
         return 0.4375;
      } else if(tansformationList.equals(getChain8_j1())) {
         System.out.println(getChain8_j1());
         return 0.4375;
      } else if(tansformationList.equals(getChain8_1_j1())) {
         System.out.println(getChain8_1_j1());
         return 0.4375;
      } else if(tansformationList.equals(getChain8_h1())) {
         System.out.println(getChain8_h1());
         return 0.4375;
      } else if(tansformationList.equals(getChain8_1_h1())) {
         System.out.println(getChain8_1_h1());
         return 0.4375;
      } else if(tansformationList.equals(getChain8_j1_h1())) {
         System.out.println(getChain8_j1_h1());
         return 0.4375;
      } else if(tansformationList.equals(getChain8_1_j1_h1())) {
         System.out.println(getChain8_1_j1_h1());
         return 0.4375;
      } else if(tansformationList.equals(getChain9())) {
         System.out.println(getChain9());
         return 0.4375;
      } else if(tansformationList.equals(getChain9_j1())) {
         System.out.println(getChain9_j1());
         return 0.4375;
      } else if(tansformationList.equals(getChain9_h1())) {
         System.out.println(getChain9_h1());
         return 0.4375;
      } else if(tansformationList.equals(getChain9_j1_h1())) {
         System.out.println(getChain9_j1_h1());
         return 0.4375;
      } else if(tansformationList.equals(getChain10())) {
         System.out.println(getChain10());
         return 0.4375;
      } else if(tansformationList.equals(getChain10_1())) {
         System.out.println(getChain10_1());
         return 0.4375;
      } else if(tansformationList.equals(getChain10_t1())) {
         System.out.println(getChain10_t1());
         return 0.4375;
      } else if(tansformationList.equals(getChain10_1_t1())) {
         System.out.println(getChain10_1_t1());
         return 0.4375;
      } else if(tansformationList.equals(getChain10_h1())) {
         System.out.println(getChain10_h1());
         return 0.4375;
      } else if(tansformationList.equals(getChain10_1_h1())) {
         System.out.println(getChain10_1_h1());
         return 0.4375;
      } else if(tansformationList.equals(getChain10_t1_h1())) {
         System.out.println(getChain10_t1_h1());
         return 0.4375;
      } else if(tansformationList.equals(getChain10_1_t1_h1())) {
         System.out.println(getChain10_1_t1_h1());
         return 0.4375;
      } else if(tansformationList.equals(getChain11())) {
         System.out.println(getChain11());
         return 0.1875;
      } else if(tansformationList.equals(getChain11_1())) {
         System.out.println(getChain11_1());
         return 0.1875;
      } else if(tansformationList.equals(getChain11_t1())) {
         System.out.println(getChain11_t1());
         return 0.1875;
      } else if(tansformationList.equals(getChain11_1_t1())) {
         System.out.println(getChain11_1_t1());
         return 0.1875;
      } else if(tansformationList.equals(getChain12())) {
         System.out.println(getChain12());
         return 0.4375;
      } else if(tansformationList.equals(getChain12_t1())) {
         System.out.println(getChain12_t1());
         return 0.4375;
      } else if(tansformationList.equals(getChain12_h1())) {
         System.out.println(getChain12_h1());
         return 0.4375;
      } else if(tansformationList.equals(getChain12_t1_h1())) {
         System.out.println(getChain12_t1_h1());
         return 0.4375;
      } else if(tansformationList.equals(getChain13())) {
         System.out.println(getChain13());
         return 0.1875;
      } else if(tansformationList.equals(getChain13_t1())) {
         System.out.println(getChain13_t1());
         return 0.1875;
      } else {
         return 0;
      }
   }

   public static HashMap<String, Double> getSimilarityMM() {
      final HashMap<String, Double> similarityMM = new HashMap<>();

      similarityMM.put("KM32Ecore", 0.12779);
      similarityMM.put("KM32Ecore1", 0.12779);
      similarityMM.put("JavaSource12Table1", 0.33);
      similarityMM.put("KM32JavaSource", 0.10173);
      similarityMM.put("KM32JavaSource1", 0.10173);
      similarityMM.put("Ecore2JavaSource", 0.1796);
      similarityMM.put("Ecore12JavaSource", 0.1796);
      similarityMM.put("Ecore2JavaSource1", 0.1796);
      similarityMM.put("Ecore12JavaSource1", 0.1796);
      similarityMM.put("KM32JavaSource2", 0.10173);
      similarityMM.put("KM32JavaSource12", 0.10173);
      similarityMM.put("Ecore2JavaSource2", 0.1796);
      similarityMM.put("Ecore12JavaSource2", 0.1796);
      similarityMM.put("Ecore2JavaSource12", 0.1796);
      similarityMM.put("Ecore12JavaSource12", 0.1796);
      similarityMM.put("JavaSource2Table", 0.33);
      similarityMM.put("JavaSource22Table", 0.33);
      similarityMM.put("JavaSource122Table", 0.33);
      similarityMM.put("JavaSource22Table1", 0.33);
      similarityMM.put("JavaSource122Table1", 0.33);
      similarityMM.put("Table2HTML", 0.39562);
      similarityMM.put("Table12HTML", 0.39562);
      similarityMM.put("Table2HTML1", 0.39562);
      similarityMM.put("Table12HTML1", 0.39562);
      similarityMM.put("HTML2XML", 0.2902);
      similarityMM.put("HTML12XML", 0.2902);
      similarityMM.put("JavaSource2XML", 0.0788);
      similarityMM.put("JavaSource12XML", 0.0788);
      similarityMM.put("JavaSource22XML", 0.0788);
      similarityMM.put("JavaSource122XML", 0.0788);
      similarityMM.put("KM32XML", 0.19173);
      similarityMM.put("Table2XML", 0.29675);
      similarityMM.put("Table12XML", 0.29675);
      similarityMM.put("JavaSource2HTML", 0.1462);
      similarityMM.put("JavaSource12HTML", 0.1462);
      similarityMM.put("JavaSource2HTML1", 0.1462);
      similarityMM.put("JavaSource12HTML1", 0.1462);
      similarityMM.put("JavaSource22HTML", 0.1462);
      similarityMM.put("JavaSource122HTML", 0.1462);
      similarityMM.put("JavaSource22HTML1", 0.1462);
      similarityMM.put("JavaSource122HTML1", 0.1462);
      similarityMM.put("Ecore2Table", 0.36887);
      similarityMM.put("Ecore12Table", 0.36887);
      similarityMM.put("KM32Table", 0.2758);
      similarityMM.put("Ecore2Table1", 0.36887);
      similarityMM.put("Ecore12Table1", 0.36887);
      similarityMM.put("KM32Table1", 0.2758);

      final File file = new File("similarityvalues.txt");
      BufferedWriter bf = null;

      try {

         bf = new BufferedWriter(new FileWriter(file));

         // iterate map entries
         for(final Map.Entry<String, Double> entry : similarityMM.entrySet()) {

            // put key and value separated by a colon
            bf.write(entry.getKey() + ":" + entry.getValue());

            // new line
            bf.newLine();
         }

         // bf.flush();
         bf.close();
      } catch(final IOException e) {
         e.printStackTrace();
      } finally {

         try {

         } catch(final Exception e) {
         }
      }

      return similarityMM;
   }

   public static LinkedHashMap<String, Double> HashMapFromTextFile() {

      final LinkedHashMap<String, Double> map = new LinkedHashMap<>();
      BufferedReader br = null;

      try {

         // create file object
         final File file = new File(filePath);

         // create BufferedReader object from the File
         br = new BufferedReader(new FileReader(file));

         String line = null;

         // read file line by line
         while((line = br.readLine()) != null) {

            // split the line by :
            final String[] parts = line.split(":");

            // first part is name, second is number
            final String name = parts[0].trim();
            final String number = parts[1].trim();
            final double number1 = NumberUtils.toDouble(number);

            // put name, number in HashMap if they are
            // not empty
            if(!name.equals("") && number1 >= 0) {
               map.put(name, number1);
            }
         }
      } catch(final Exception e) {
         e.printStackTrace();
      } finally {

         // Always close the BufferedReader
         if(br != null) {
            try {
               br.close();
            } catch(final Exception e) {
            }
            ;
         }
      }

      return map;
   }

   public static LinkedHashMap<String, Integer> HashMapFromTextFile_complexity() {

      final LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
      BufferedReader br = null;

      try {

         // create file object
         final File file = new File(filePath_complexity);

         // create BufferedReader object from the File
         br = new BufferedReader(new FileReader(file));

         String line = null;

         // read file line by line
         while((line = br.readLine()) != null) {

            // split the line by :
            final String[] parts = line.split(":");

            // first part is name, second is number
            final String name = parts[0].trim();
            final String number = parts[1].trim();
            final int number1 = NumberUtils.toInt(number);

            // put name, number in HashMap if they are
            // not empty
            if(!name.equals("") && number1 >= 0) {
               map.put(name, number1);
            }
         }
      } catch(final Exception e) {
         e.printStackTrace();
      } finally {

         // Always close the BufferedReader
         if(br != null) {
            try {
               br.close();
            } catch(final Exception e) {
            }
            ;
         }
      }

      return map;
   }

   public static LinkedHashMap<String, Double> HashMapFromTextFile2() {

      final LinkedHashMap<String, Double> map = new LinkedHashMap<>();
      BufferedReader br = null;

      try {

         // create file object
         final File file = new File(filePath2);

         // create BufferedReader object from the File
         br = new BufferedReader(new FileReader(file));

         String line = null;

         // read file line by line
         while((line = br.readLine()) != null) {

            // split the line by :
            final String[] parts = line.split(":");

            // first part is name, second is number
            final String name = parts[0].trim();
            final String number = parts[1].trim();
            final double number1 = NumberUtils.toDouble(number);

            // put name, number in HashMap if they are
            // not empty
            if(!name.equals("") && number1 >= 0) {
               map.put(name, number1);
            }
         }
      } catch(final Exception e) {
         e.printStackTrace();
      } finally {

         // Always close the BufferedReader
         if(br != null) {
            try {
               br.close();
            } catch(final Exception e) {
            }
            ;
         }
      }

      return map;
   }

   public static TransformationModel loadModel(final String model) {
      final ResourceSet resSet = new ResourceSetImpl();
      final Resource resource = resSet.getResource(URI.createURI(model), true);
      if(resource == null) {
         System.err.println("Model can not be loaded!");
         return null;
      }
      final EObject root = resource.getContents().get(0);
      if(!(root instanceof TransformationModel)) {
         System.err.println("Model is not a TransformationModel");
         return null;
      }
      return (TransformationModel) resource.getContents().get(0);
   }

   public static void main(final String[] args) throws Exception {
      if(args.length == 0) {
         System.err.println("Please provide the model as parameter.");
         System.err.println("Example: java -jar CRAIndexCalculator.jar ToEvaluate.xmi");
         return;
      }
      // addrepo();
      // TransformationChainModelGenerator.main(null);
      getSimilarityMM();
      // calccov();
      evaluateModel(args[0]);
      // addrepo();
   }

   public static void printGeneralInfo(final TransformationModel model) {
      final int nrTransformations = model.getModelTransformationRepository().getTransformations().size();
      final int nrMetamodels = model.getModelTransformationRepository().getMetamodels().size();

      final String metamodelList = model.getModelTransformationRepository().getMetamodels().stream().map(e -> e.getId())
            .reduce(", ", String::concat);
      final String transformationList = model.getModelTransformationRepository().getTransformations().stream()
            .map(e -> e.getSrc() + " -> " + e.getTarget()).reduce("\n", String::concat);

      System.out.println("------------------------------------------");
      System.out.println("General Info");
      System.out.println("------------------------------------------");
      System.out.println("|Transformations|    = " + nrTransformations);
      System.out.println("|Metamodels|    = " + nrMetamodels);
      System.out.println("Metamodels    = " + metamodelList);
      System.out.println("Transformations    = " + transformationList);
      System.out.println("------------------------------------------\n");
   }

   public static void printOptimalityInfo(final TransformationModel model) throws Exception {
      final double trafoCoverage = calculateTransformationCoverage(model);
      System.out.println("------------------------------------------");
      System.out.println("Optimality");
      System.out.println("------------------------------------------");
      System.out.println("The aggregated transformation coverage is: " + trafoCoverage);
      System.out.println("------------------------------------------\n");
   }

   public static void registerPackage() {
      TrafochainselectionPackage.eINSTANCE.eClass();

      final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
      final Map<String, Object> m = reg.getExtensionToFactoryMap();
      m.put("xmi", new XMIResourceFactoryImpl());
   }

   /* TODO Calculation of transformation coverage for transformation chain */
   /*
    * public static double calculateTransformationCoverage(final TransformationModel model) {
    * final List<Double> coverageList = new ArrayList<>();
    * for(final Transformation t : model.getTransformationchain().getUses()) {
    * final String atl = String.format("resources/running_example/transformations/%s2%s.atl", t.getSrc().getId(),
    * t.getTarget().getId());
    * final String inputMM = String.format("resources/running_example/metamodels/%s.ecore", t.getSrc().getId());
    * final it.univaq.disim.business.datamodel.Transformation dTransformation = new
    * it.univaq.disim.business.datamodel.Transformation();
    * dTransformation.setATLTransformation(atl);
    * dTransformation.setInputMetamodel(inputMM);
    * dTransformation.isMeta_Metamodel(true);
    * coverageList.add((double) Coverage.coverage(dTransformation));
    * }
    * final double finalCov = coverageList.stream().reduce(1.0, (acc, value) -> acc * value).doubleValue();
    * return 0;
    * }
    */

   // private static void printCorrectnessInfo(final ClassModel model) {
   // System.out.println("------------------------------------------");
   // System.out.println("Correctness");
   // System.out.println("------------------------------------------");
   // final boolean classNames = checkAllClassesDifferentNames(model);
   // final boolean featuresEncapsulated = checkAllFeaturesEncapsulated(model);
   // if(!classNames && !featuresEncapsulated) {
   // System.out.println("Correctness: ok");
   // } else {
   // System.out.println("Correctness: Violations found.");
   // }
   // System.out.println("------------------------------------------\n");
   // }

   private FitnessCalculator() {
   }
}
