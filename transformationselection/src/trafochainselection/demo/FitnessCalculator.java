package trafochainselection.demo;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.epsilon.emc.emf.AbstractEmfModel;
import org.eclipse.epsilon.emc.emf.EmfMetaModel;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.EolModule;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.etl.EtlModule;
import org.eclipse.epsilon.etl.chain.selection.Chaining_MT;

import trafochainselection.TrafochainselectionPackage;
import trafochainselection.Transformation;
import trafochainselection.TransformationModel;

public final class FitnessCalculator {

   static Path metamodelsRoot = Paths.get("metamodels");
   static Path scriptRoot = Paths.get("scripts");
   static File scriptPath = new File("scripts");

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
      org.apache.commons.io.FileUtils.cleanDirectory(Path.of("models", "tmp").toFile());

      final ArrayList<String> chain = new ArrayList<>();
      for(final Transformation t : model.getTransformationchain().getUses()) {
         chain.add(t.getId());
      }

      String currentInputModel = null;
      String outputModelPath = null;
      String targetMMIdentifier = null;

      for(final Transformation t : model.getTransformationchain().getUses()) {

         final String sourceMMIdentifier = extractFileNameWithoutExtension(t.getSrc().getId());
         targetMMIdentifier = extractFileNameWithoutExtension(t.getTarget().getId());
         outputModelPath = "models/tmp/out-" + sourceMMIdentifier + "-to-" + targetMMIdentifier + ".xmi";

         final String trafoName = sourceMMIdentifier + "2" + targetMMIdentifier + ".etl";
         // trafo, inp_model, t.scr.id.name,t.src.id, out_model, t.trg.id.name, t.trg.id

         Transformer.transform(Path.of("transformations", "i1", trafoName),
               currentInputModel == null ? "models/sample-km3.xmi" : currentInputModel, sourceMMIdentifier,
               Path.of("metamodels", sourceMMIdentifier + ".ecore").toString(), outputModelPath, targetMMIdentifier,
               Path.of("metamodels", targetMMIdentifier + ".ecore").toString());

         currentInputModel = outputModelPath;

      }

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

         final String currentCoverage = outputStream.toString();
         return Double.valueOf(currentCoverage);
      } catch(final Exception e) {
         e.printStackTrace();
      }

      throw new RuntimeException("Error computing model coverage!");
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

   public static String extractFileNameWithoutExtension(final String filePath) {
      // Using FileSystems to handle different file separators
      final Path path = FileSystems.getDefault().getPath(filePath);

      // Get the file name (including extension) from the path
      final String fileNameWithExtension = path.getFileName().toString();

      // Find the last occurrence of the file separator
      final int lastSeparatorIndex = fileNameWithExtension.lastIndexOf('\\');

      // Extract the part after the last separator (file name with extension)
      final String fileName = lastSeparatorIndex == -1 ? fileNameWithExtension
            : fileNameWithExtension.substring(lastSeparatorIndex + 1);

      // Find the last occurrence of the dot (.) to exclude the extension
      final int lastDotIndex = fileName.lastIndexOf('.');

      // Extract the part before the last dot (file name without extension)
      final String mmName = lastDotIndex == -1 ? fileName : fileName.substring(0, lastDotIndex);

      if(mmName.startsWith("KM3")) {
         return "KM3";
      }
      // Define a regular expression pattern to match trailing digits
      final Pattern pattern = Pattern.compile("\\d+$");

      // Create a matcher for the input string
      final Matcher matcher = pattern.matcher(mmName);

      return matcher.find() ? mmName.substring(0, matcher.start()) : mmName;

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

   public static HashMap<String, Double> getSimilarityMM() {
      final HashMap<String, Double> similarityMM = new HashMap<>();

      similarityMM.put("JavaSource12Table", 0.33);
      similarityMM.put("JavaSource2Table1", 0.33);

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

   static IModel loadModel(final String modelName, final String modelPath) {
      // Implement the logic to load your models here.
      // You may use the Epsilon model loaders specific to your model types.
      // For example, EMFModel, CSVModel, or others depending on your models.

      // Example for loading an EMF model:
      final EmfModel emfModel = new EmfModel();
      emfModel.setName(modelName);
      emfModel.setModelFile(modelPath);
      try {
         emfModel.load();
      } catch(final EolModelLoadingException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      // Return the loaded model
      return emfModel;

      // Modify this according to the type of models you are using.
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

   public static String registerMM(final String mm) {
      try {
         // register globally the Ecore Resource Factory to the ".ecore" extension
         Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new EcoreResourceFactoryImpl());

         final ResourceSet rs = new ResourceSetImpl();

         final ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(rs.getPackageRegistry());
         rs.getLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
         final URI fileURI = URI.createFileURI(mm);
         final Resource r = rs.getResource(fileURI, true);
         final EObject eObject = r.getContents().get(0);
         if(eObject instanceof EPackage) {
            final EPackage p = (EPackage) eObject;
            EPackage.Registry.INSTANCE.put(p.getNsURI(), p);
            return p.getNsURI();
         }

      } catch(final Exception e) {
         // TODO Auto-generated catch block
         // e.printStackTrace();
         System.err.println(e.getMessage());
      }
      return null;
   }

   public static void registerPackage() {
      TrafochainselectionPackage.eINSTANCE.eClass();

      final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
      final Map<String, Object> m = reg.getExtensionToFactoryMap();
      m.put("xmi", new XMIResourceFactoryImpl());
   }

   private FitnessCalculator() {
   }
}
