package trafochainselection.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;
import org.eclipse.epsilon.etl.chain.selection.Chain_MT;
import org.eclipse.epsilon.etl.chain.selection.Chaining_MT;

import trafochainselection.Metamodel;
import trafochainselection.ModelTransformationRepository;
import trafochainselection.TrafochainselectionPackage;
import trafochainselection.Transformation;
import trafochainselection.TransformationChain;
import trafochainselection.TransformationModel;

public class TransformationChainModelGenerator {

   static Path modelsRoot = Paths.get("models");
   static Path scriptRoot = Paths.get("scripts");
   static Path metamodelsRoot = Paths.get("metamodels");

   static Path genmodelsRoot = Paths.get("models/generatedmodels");

   static File metamodelPath = new File("metamodels");

   static String sourceMM = metamodelsRoot.resolve("KM3.ecore").toString();
   static String targetMM = metamodelsRoot.resolve("XML.ecore").toString();
   static String sourceModel = modelsRoot.resolve("sample-km3.xmi").toString();
   static String targetModel = genmodelsRoot.resolve("sample-xml.xmi").toString();

   // static String sourceMM = metamodelsRoot.resolve("ABC.ecore").toString();
   // static String targetMM = metamodelsRoot.resolve("GH.ecore").toString();
   // static String sourceModel = modelsRoot.resolve("abc1.xmi").toAbsolutePath().toUri().toString();
   // static String targetModel = genmodelsRoot.resolve("gh.model").toAbsolutePath().toUri().toString();

   // static String sourceMM = metamodelsRoot.resolve("Java.ecore").toString();
   // static String targetMM = metamodelsRoot.resolve("Graph1.ecore").toString();
   // static String sourceModel = modelsRoot.resolve("eclipseModel-0.5.xmi").toAbsolutePath().toUri().toString();
   // static String targetModel = genmodelsRoot.resolve("sample-graph1.xmi").toAbsolutePath().toUri().toString();

   // static String sourceMM = metamodelsRoot.resolve("Tree.ecore").toString();
   // static String targetMM = metamodelsRoot.resolve("DB.ecore").toString();
   // static String sourceModel = modelsRoot.resolve("Tree.xmi").toAbsolutePath().toUri().toString();
   // static String targetModel = genmodelsRoot.resolve("DB.xmi").toAbsolutePath().toUri().toString();

   final static String filePath = "C:\\Users\\sigi\\Desktop\\uni\\CDL_Mint\\Projekte\\trafo-chain-selection\\org.eclipse.epsilon.etl.chain.optimisation\\write.txt";

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

   public static void main(final String[] args) throws Exception {
      // public static void modelgenerator() throws Exception {
      final Chaining_MT chainingMt = new Chaining_MT();
      // EtlChainOptimiser.calculateTransformationCoverageOnOptimizedTransformation();
      final ArrayList<String> two = chainingMt.identifyChain_two(sourceModel, sourceMM, targetModel, targetMM);
      List<ArrayList<String>> chain = chainingMt.identifyChain(sourceModel, sourceMM, targetModel, targetMM);

      if(scriptRoot.resolve("Java2XML.etl").toFile().exists()) {
         if(!scriptRoot.resolve("Table2XML.etl").toFile().exists()) {
            // Files.move(scriptRoot.resolve("Java2XML.etl"), modelsRoot.resolve("Java2XML.etl"));
            chain.addAll(chainingMt.identifyChain(sourceModel, sourceMM, targetModel, targetMM));
         } else if(scriptRoot.resolve("Table2XML.etl").toFile().exists()) {
            // Files.move(scriptRoot.resolve("Java2XML.etl"), modelsRoot.resolve("Java2XML.etl"));
            Files.move(scriptRoot.resolve("Table2XML.etl"), modelsRoot.resolve("Table2XML.etl"));
            chain.addAll(chainingMt.identifyChain(sourceModel, sourceMM, targetModel, targetMM));
            Files.move(modelsRoot.resolve("Table2XML.etl"), scriptRoot.resolve("Table2XML.etl"));
         }
         // Files.move(modelsRoot.resolve("Java2XML.etl"), scriptRoot.resolve("Java2XML.etl"));
         if(!scriptRoot.resolve("Java2Table.etl").toFile().exists()) {
            // Files.move(scriptRoot.resolve("Java2XML.etl"), modelsRoot.resolve("Java2XML.etl"));
            chain.addAll(chainingMt.identifyChain(sourceModel, sourceMM, targetModel, targetMM));
         } else if(scriptRoot.resolve("Java2Table.etl").toFile().exists()) {
            // Files.move(scriptRoot.resolve("Java2XML.etl"), modelsRoot.resolve("Java2XML.etl"));
            Files.move(scriptRoot.resolve("Java2Table.etl"), modelsRoot.resolve("Java2Table.etl"));
            chain.addAll(chainingMt.identifyChain(sourceModel, sourceMM, targetModel, targetMM));
            Files.move(modelsRoot.resolve("Java2Table.etl"), scriptRoot.resolve("Java2Table.etl"));
         }
         // Files.move(modelsRoot.resolve("Java2XML.etl"), scriptRoot.resolve("Java2XML.etl"));
         if(!scriptRoot.resolve("Table2XML.etl").toFile().exists()) {
            // Files.move(scriptRoot.resolve("Java2XML.etl"), modelsRoot.resolve("Java2XML.etl"));
            chain.addAll(chainingMt.identifyChain(sourceModel, sourceMM, targetModel, targetMM));
         } else if(scriptRoot.resolve("Table2XML.etl").toFile().exists()) {
            Files.move(scriptRoot.resolve("Table2HTML.etl"), modelsRoot.resolve("Table2HTML.etl"));
            Files.move(scriptRoot.resolve("HTML2XML.etl"), modelsRoot.resolve("HTML2XML.etl"));
            // Files.move(scriptRoot.resolve("Table2XML.etl"), modelsRoot.resolve("Table2XML.etl"));
            chain.addAll(chainingMt.identifyChain(sourceModel, sourceMM, targetModel, targetMM));
            // Files.move(modelsRoot.resolve("Table2XML.etl"), scriptRoot.resolve("Table2XML.etl"));
            Files.move(modelsRoot.resolve("Table2HTML.etl"), scriptRoot.resolve("Table2HTML.etl"));
            Files.move(modelsRoot.resolve("HTML2XML.etl"), scriptRoot.resolve("HTML2XML.etl"));
         }
         // Files.move(modelsRoot.resolve("Java2XML.etl"), scriptRoot.resolve("Java2XML.etl"));
         Files.move(scriptRoot.resolve("Table2HTML.etl"), modelsRoot.resolve("Table2HTML.etl"));
         chain.addAll(chainingMt.identifyChain(sourceModel, sourceMM, targetModel, targetMM));
         Files.move(modelsRoot.resolve("Table2HTML.etl"), scriptRoot.resolve("Table2HTML.etl"));
      }

      if(scriptRoot.resolve("EMF2Table.etl").toFile().exists()) {
         if(!scriptRoot.resolve("km3_2_java.etl").toFile().exists()) {
            // Files.move(scriptRoot.resolve("EMF2Table.etl"), modelsRoot.resolve("EMF2Table.etl"));
            chain.addAll(chainingMt.identifyChain(sourceModel, sourceMM, targetModel, targetMM));
         }
         if(scriptRoot.resolve("km3_2_java.etl").toFile().exists()) {
            // Files.move(scriptRoot.resolve("EMF2Table.etl"), modelsRoot.resolve("EMF2Table.etl"));
            Files.move(scriptRoot.resolve("km3_2_java.etl"), modelsRoot.resolve("km3_2_java.etl"));
            chain.addAll(chainingMt.identifyChain(sourceModel, sourceMM, targetModel, targetMM));
            Files.move(modelsRoot.resolve("km3_2_java.etl"), scriptRoot.resolve("km3_2_java.etl"));
         }
         // Files.move(modelsRoot.resolve("EMF2Table.etl"), scriptRoot.resolve("EMF2Table.etl"));
         if(!scriptRoot.resolve("KM32EMF.etl").toFile().exists()) {
            // Files.move(scriptRoot.resolve("EMF2Table.etl"), modelsRoot.resolve("EMF2Table.etl"));
            chain.addAll(chainingMt.identifyChain(sourceModel, sourceMM, targetModel, targetMM));
         }
         if(scriptRoot.resolve("KM32EMF.etl").toFile().exists()) {
            // Files.move(scriptRoot.resolve("EMF2Table.etl"), modelsRoot.resolve("EMF2Table.etl"));
            Files.move(scriptRoot.resolve("KM32EMF.etl"), modelsRoot.resolve("KM32EMF.etl"));
            chain.addAll(chainingMt.identifyChain(sourceModel, sourceMM, targetModel, targetMM));
            Files.move(modelsRoot.resolve("KM32EMF.etl"), scriptRoot.resolve("KM32EMF.etl"));
         }
         // Files.move(modelsRoot.resolve("EMF2Table.etl"), scriptRoot.resolve("EMF2Table.etl"));
         Files.move(scriptRoot.resolve("EMF2Java.etl"), modelsRoot.resolve("EMF2Java.etl"));
         chain.addAll(chainingMt.identifyChain(sourceModel, sourceMM, targetModel, targetMM));
         Files.move(modelsRoot.resolve("EMF2Java.etl"), scriptRoot.resolve("EMF2Java.etl"));
      }

      if(scriptRoot.resolve("Java2HTML.etl").toFile().exists()) {
         Files.move(scriptRoot.resolve("Java2Table.etl"), modelsRoot.resolve("Java2Table.etl"));
         Files.move(scriptRoot.resolve("Table2HTML.etl"), modelsRoot.resolve("Table2HTML.etl"));
         Files.move(scriptRoot.resolve("Table2XML.etl"), modelsRoot.resolve("Table2XML.etl"));
         Files.move(scriptRoot.resolve("Java2XML.etl"), modelsRoot.resolve("Java2XML.etl"));
         chain.addAll(chainingMt.identifyChain(sourceModel, sourceMM, targetModel, targetMM));
         Files.move(modelsRoot.resolve("Java2Table.etl"), scriptRoot.resolve("Java2Table.etl"));
         Files.move(modelsRoot.resolve("Table2HTML.etl"), scriptRoot.resolve("Table2HTML.etl"));
         Files.move(modelsRoot.resolve("Table2XML.etl"), scriptRoot.resolve("Table2XML.etl"));
         Files.move(modelsRoot.resolve("Java2XML.etl"), scriptRoot.resolve("Java2XML.etl"));
      }

      if(scriptRoot.resolve("EMF2Table.etl").toFile().exists()) {
         Files.move(scriptRoot.resolve("km3_2_java.etl"), modelsRoot.resolve("km3_2_java.etl"));
         Files.move(scriptRoot.resolve("EMF2Java.etl"), modelsRoot.resolve("EMF2Java.etl"));
         chain.addAll(chainingMt.identifyChain(sourceModel, sourceMM, targetModel, targetMM));
         Files.move(modelsRoot.resolve("km3_2_java.etl"), scriptRoot.resolve("km3_2_java.etl"));
         Files.move(modelsRoot.resolve("EMF2Java.etl"), scriptRoot.resolve("EMF2Java.etl"));

         Files.move(scriptRoot.resolve("km3_2_java.etl"), modelsRoot.resolve("km3_2_java.etl"));
         Files.move(scriptRoot.resolve("EMF2Java.etl"), modelsRoot.resolve("EMF2Java.etl"));
         Files.move(scriptRoot.resolve("Table2XML.etl"), modelsRoot.resolve("Table2XML.etl"));
         chain.addAll(chainingMt.identifyChain(sourceModel, sourceMM, targetModel, targetMM));
         Files.move(modelsRoot.resolve("km3_2_java.etl"), scriptRoot.resolve("km3_2_java.etl"));
         Files.move(modelsRoot.resolve("EMF2Java.etl"), scriptRoot.resolve("EMF2Java.etl"));
         Files.move(modelsRoot.resolve("Table2XML.etl"), scriptRoot.resolve("Table2XML.etl"));
      }

      if(scriptRoot.resolve("KM32Table.etl").toFile().exists()) {
         Files.move(scriptRoot.resolve("km3_2_java.etl"), modelsRoot.resolve("km3_2_java.etl"));
         Files.move(scriptRoot.resolve("KM32EMF.etl"), modelsRoot.resolve("KM32EMF.etl"));
         Files.move(scriptRoot.resolve("EMF2Java.etl"), modelsRoot.resolve("EMF2Java.etl"));
         Files.move(scriptRoot.resolve("Java2Table.etl"), modelsRoot.resolve("Java2Table.etl"));
         chain.addAll(chainingMt.identifyChain(sourceModel, sourceMM, targetModel, targetMM));
         Files.move(modelsRoot.resolve("km3_2_java.etl"), scriptRoot.resolve("km3_2_java.etl"));
         Files.move(modelsRoot.resolve("KM32EMF.etl"), scriptRoot.resolve("KM32EMF.etl"));
         Files.move(modelsRoot.resolve("EMF2Java.etl"), scriptRoot.resolve("EMF2Java.etl"));
         Files.move(modelsRoot.resolve("Java2Table.etl"), scriptRoot.resolve("Java2Table.etl"));
      }

      chain = chain.stream().distinct().collect(Collectors.toList());

      final Chain_MT cm = new Chain_MT();
      final boolean etl1 = cm.findETLinModels(sourceMM, targetMM);
      // System.out.println(identifyETLinModels(sourceMM, targetMM));
      if(etl1) {
         for(int id = 0; id < cm.identifyETLinModels(sourceMM, targetMM).size(); id++) {
            final ArrayList<String> x = cm.identifyETLinModels(sourceMM, targetMM);
            // System.out.println("qwerty: "+x);
            Files.move(modelsRoot.resolve(x.get(id)), scriptRoot.resolve(x.get(id)));
            // break;
         }
      }
      if(!two.isEmpty()) {
         chain.add(two);
      }
      final ArrayList<String> samemm = new ArrayList<>();
      if(chainingMt.findETL(targetMM, targetMM)) {
         samemm.add(targetMM.substring(targetMM.indexOf("\\") + 1, targetMM.length()));
         samemm.add(targetMM.substring(targetMM.indexOf("\\") + 1, targetMM.length()));
         chain.add(samemm);
      }
      // prints arraylist of arraylist of metamodels which are used in the model transformation chain
      System.out.println("Chains: " + chain);

      // Define resource set for this ecore model
      final HenshinResourceSet rSet = new HenshinResourceSet();
      rSet.getPackageRegistry().put(TrafochainselectionPackage.eNS_URI, TrafochainselectionPackage.eINSTANCE);

      // Define classifiers for needed model elements
      final EClassifier tmClassifier = TrafochainselectionPackage.eINSTANCE.getEClassifier("TransformationModel");
      final EClassifier tcClassifier = TrafochainselectionPackage.eINSTANCE.getEClassifier("TransformationChain");
      final EClassifier mtrClassifier = TrafochainselectionPackage.eINSTANCE
            .getEClassifier("ModelTransformationRepository");
      final EClassifier tClassifier = TrafochainselectionPackage.eINSTANCE.getEClassifier("Transformation");
      final EClassifier mmClassifier = TrafochainselectionPackage.eINSTANCE.getEClassifier("Metamodel");

      // Instantiate elements using EcoreUtil class
      final TransformationModel tm = (TransformationModel) EcoreUtil.create((EClass) tmClassifier);
      final TransformationChain tc = (TransformationChain) EcoreUtil.create((EClass) tcClassifier);
      final ModelTransformationRepository mtr = (ModelTransformationRepository) EcoreUtil
            .create((EClass) mtrClassifier);

      // e.g., set metamodel attribute "id"
      tc.setId("chain1");

      ArrayList<String> models = new ArrayList<>();
      ArrayList<Metamodel> meta1 = new ArrayList<>();
      ArrayList<Metamodel> meta2 = new ArrayList<>();

      for(final ArrayList<String> element : chain) {
         for(int j = 0; j < element.size(); j++) {

            models.add(element.get(j));
         }
      }

      // final Transformation t1 = (Transformation) EcoreUtil.create((EClass) tClassifier);
      // final Metamodel mm1 = (Metamodel) EcoreUtil.create((EClass) mmClassifier);
      // final Metamodel mm2 = (Metamodel) EcoreUtil.create((EClass) mmClassifier);
      // if(j + 1 < chain.get(i).size()) {
      //
      // mm1.setId(metamodelsRoot.resolve(chain.get(i).get(j)).toString());
      //
      // mm2.setId(metamodelsRoot.resolve(chain.get(i).get(j + 1)).toString());
      //
      // t1.setId(chain.get(i).get(j).substring(0, chain.get(i).get(j).indexOf(".")) + "2"
      // + chain.get(i).get(j + 1).substring(0, chain.get(i).get(j + 1).indexOf(".")));
      // t1.setSrc(mm1);
      // t1.setTarget(mm2);
      // meta1.add(mm1);
      // meta2.add(mm2);
      // // System.out.println(t1.getSrc() + " " + t1.getTarget());
      //
      // if(mm1.getId().equals(sourceMM)) {
      // tc.setStart(mm1);
      // }
      // if(mm2.getId().equals(targetMM)) {
      // tc.setFinal(mm2);
      // }
      //
      // mtr.getMetamodels().add(mm1);
      // mtr.getMetamodels().add(mm2);
      // mtr.getTransformations().add(t1);
      // // tc.getUses().add(t1);
      //
      // }
      //
      // }
      // }

      final Set<String> set = new LinkedHashSet<>();
      set.addAll(models);
      models.clear();
      models.addAll(set);
      models = (ArrayList<String>) models.stream().distinct().collect(Collectors.toList());
      // System.out.println(models);
      for(int m = 0; m < models.size() - 1; m++) {

         for(int n = m + 1; n < models.size(); n++) {

            final Transformation t1 = (Transformation) EcoreUtil.create((EClass) tClassifier);
            final Transformation t2 = (Transformation) EcoreUtil.create((EClass) tClassifier);
            final Metamodel mm1 = (Metamodel) EcoreUtil.create((EClass) mmClassifier);
            final Metamodel mm2 = (Metamodel) EcoreUtil.create((EClass) mmClassifier);

            final LinkedHashMap<String, Double> mapFromFile = HashMapFromTextFile();
            final HashMap<String, Integer> complexitymt = chainingMt.mt_complexity();

            // for(int i = 0; i < chainingMt
            // .identifyETL(metamodelPath + "/" + models.get(m), metamodelPath + "/" + models.get(n)).size(); i++) {
            if(chainingMt.findETL(metamodelsRoot.resolve(models.get(m)).toString(),
                  metamodelsRoot.resolve(models.get(n)).toString())) {

               mm1.setId(metamodelsRoot.resolve(models.get(m)).toString());
               // (Metamodel) EcoreUtil.
               meta1.add(mm1);
               // System.out.println(meta1);
               mm2.setId(metamodelsRoot.resolve(models.get(n)).toString());
               meta2.add(mm2);
               // System.out.println(meta2);
               // for(final String etl : chainingMt.identifyETL(metamodelsRoot.resolve(models.get(m)).toString(),
               // metamodelsRoot.resolve(models.get(n)).toString())) {

               t1.setId(models.get(m).substring(0, models.get(m).indexOf(".")) + "2"
                     + models.get(n).substring(0, models.get(n).indexOf(".")));

               // System.out.println(models.get(m) + " " + models.get(n));
               // System.out.println(
               // chainingMt.identifyETL(metamodelPath + "/" + models.get(m), metamodelPath + "/" + models.get(n)));
               for(final String element : chainingMt.identifyETL(metamodelPath + "/" + models.get(m),
                     metamodelPath + "/" + models.get(n))) {
                  t1.setCoverage(mapFromFile.get(element));
                  t1.setComplexity(complexitymt.get(element));

               }

               meta1 = (ArrayList<Metamodel>) meta1.stream().distinct().collect(Collectors.toList());
               meta2 = (ArrayList<Metamodel>) meta2.stream().distinct().collect(Collectors.toList());

               if(meta1.size() > 1) {
                  for(int m1 = 0; m1 < meta1.size() - 1; m1++) {
                     if(meta1.get(m1).getId().equals(mm1.getId())) {
                        t1.setSrc(meta1.get(m1));
                        if(meta1.get(m1).getId().equals(sourceMM)) {
                           tc.setStart(meta1.get(m1));
                        }
                        mtr.getMetamodels().add(meta1.get(m1));
                        EcoreUtil.remove(mm1);
                        break;
                     } else {
                        for(int m2 = 0; m2 < meta2.size() - 1; m2++) {
                           // if(!meta2.get(m2).getId().equals(mm1.getId())) {
                           // t1.setSrc(mm1);
                           // if(mm1.getId().equals(sourceMM)) {
                           // tc.setStart(mm1);
                           // }
                           // mtr.getMetamodels().add(mm1);
                           // break;
                           // }
                           if(meta2.get(m2).getId().equals(mm1.getId())) {
                              t1.setSrc(meta2.get(m2));
                              if(meta2.get(m2).getId().equals(sourceMM)) {
                                 tc.setStart(meta2.get(m2));
                              }
                              mtr.getMetamodels().add(meta2.get(m2));
                              EcoreUtil.remove(mm1);
                              break;
                           } else {
                              t1.setSrc(mm1);
                              if(mm1.getId().equals(sourceMM)) {
                                 tc.setStart(mm1);
                              }
                              mtr.getMetamodels().add(mm1);
                           }
                        }
                     }
                  }
               } else {
                  t1.setSrc(mm1);
                  if(mm1.getId().equals(sourceMM)) {
                     tc.setStart(mm1);
                  }
                  mtr.getMetamodels().add(mm1);
               }

               if(meta2.size() > 1) {
                  for(int m2 = 0; m2 < meta2.size() - 1; m2++) {
                     if(meta2.get(m2).getId().equals(mm2.getId())) {
                        t1.setTarget(meta2.get(m2));
                        if(meta2.get(m2).getId().equals(targetMM)) {
                           tc.setFinal(meta2.get(m2));
                        }
                        mtr.getMetamodels().add(meta2.get(m2));
                        EcoreUtil.remove(mm2);
                        break;
                     } else {
                        for(int m1 = 0; m1 < meta1.size() - 1; m1++) {
                           // if(!meta1.get(m1).getId().equals(mm2.getId())) {
                           // t1.setTarget(mm2);
                           // if(mm2.getId().equals(targetMM)) {
                           // tc.setFinal(mm2);
                           // }
                           // mtr.getMetamodels().add(mm2);
                           // break;
                           // }
                           if(meta1.get(m1).getId().equals(mm2.getId())) {
                              t1.setTarget(meta1.get(m1));
                              if(meta1.get(m1).getId().equals(targetMM)) {
                                 tc.setFinal(meta1.get(m1));
                              }
                              mtr.getMetamodels().add(meta1.get(m1));
                              EcoreUtil.remove(mm2);
                              break;
                           }

                           else {
                              t1.setTarget(mm2);
                              if(mm2.getId().equals(targetMM)) {
                                 tc.setFinal(mm2);
                              }
                              mtr.getMetamodels().add(mm2);
                           }
                        }
                     }
                  }
               } else {
                  t1.setTarget(mm2);
                  if(mm2.getId().equals(targetMM)) {
                     tc.setFinal(mm2);
                  }
                  mtr.getMetamodels().add(mm2);
               }
               // t1.setTarget(mm2);

               mtr.getTransformations().add(t1);
               // tc.getUses().add(t1);

            }
            // }
            if(chainingMt.findETL(metamodelsRoot.resolve(models.get(n)).toString(),
                  metamodelsRoot.resolve(models.get(n)).toString())) {
               mm2.setId(metamodelsRoot.resolve(models.get(n)).toString());
               t2.setId(models.get(n).substring(0, models.get(n).indexOf(".")) + "2" + "Reduced"
                     + models.get(n).substring(0, models.get(n).indexOf(".")));
               t2.setSrc(mm2);
               t2.setTarget(mm2);
               if(mm2.getId().equals(sourceMM)) {
                  tc.setStart(mm2);
               }
               mtr.getMetamodels().add(mm2);
               mtr.getMetamodels().add(mm2);
               mtr.getTransformations().add(t2);
               // tc.getUses().add(t2);
            }
            // }
            // EcoreUtil.remove(mm2);
            // if(chainingMt.findETL(metamodelsRoot.resolve(models.get(n)).toString(),
            // metamodelsRoot.resolve(models.get(n)).toString())) {
            // // final Metamodel mm1 = (Metamodel) EcoreUtil.create((EClass) mmClassifier);
            // // final Transformation t1 = (Transformation) EcoreUtil.create((EClass) tClassifier);
            // mm1 = mm2;
            // mm1.setId(metamodelsRoot.resolve(models.get(n)).toString());
            // meta1.add(mm1);
            // // mm2.setId(metamodelsRoot.resolve(models.get(n)).toString());
            // t1.setId(models.get(n).substring(0, models.get(n).indexOf(".")) + "2"
            // + models.get(n).substring(0, models.get(n).indexOf(".")));
            // t1.setSrc(mm1);
            // t1.setTarget(mm1);
            // if(mm1.getId().equals(sourceMM)) {
            // tc.setStart(mm1);
            // }
            // mtr.getMetamodels().add(mm1);
            // mtr.getMetamodels().add(mm1);
            // mtr.getTransformations().add(t1);
            // }

            // final Set<ArrayList<String>> meta1_set = new LinkedHashSet<>();
            // meta1_set.addAll(meta1);
            // chain.clear();
            // chain.addAll(chain_set);

         }
      }

      // for(int m = 0; m < meta1.size() - 1; m++) {
      // for(int m1 = m + 1; m1 < meta1.size(); m1++) {
      // if(meta1.get(m).getId().equals(meta1.get(m1).getId())) {
      // EcoreUtil.remove(meta1.get(m1));
      // }
      // }
      // }
      // for(int m = 0; m < meta2.size() - 1; m++) {
      // for(int m1 = m + 1; m1 < meta2.size(); m1++) {
      // if(meta2.get(m).getId().equals(meta2.get(m1).getId())) {
      // EcoreUtil.remove(meta2.get(m1));
      // }
      // }
      // }
      // System.out.println(meta1);
      // System.out.println(meta2);
      // models.stream().distinct().collect(Collectors.toList());

      tm.setTransformationchain(tc);
      tm.setModelTransformationRepository(mtr);

      final Resource oR = rSet.createResource(URI.createFileURI("problem/myNewModel.xmi"));
      oR.getContents().add(tm);
      try {
         oR.save(null);
         System.out.println("Problem model created\n");
      } catch(final IOException e) {
         e.printStackTrace();
         // System.out.println(e.getMessage());
      }
   }

}
