package trafochainselection.demo;

import at.ac.tuwien.big.momot.search.engine.MomotEngine;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.UnitApplication;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.interpreter.impl.UnitApplicationImpl;
import org.eclipse.emf.henshin.model.Unit;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;

import trafochainselection.TrafochainselectionPackage;

public class TrafoTest {

   private enum T {
      ADD_FLOW, ADD_BATTERY_B1, ADD_BATTERY_B2, ADD_BLOCK, ADD_PUMP_P1, ADD_PUMP_P2, ADD_MOTOR_m1
   }

   // private final static List<T> ops = List.of(T.ADD_FLOW, T.ADD_FLOW, T.ADD_BATTERY_B2);
   private final static List<T> ops = List.of(T.ADD_FLOW, T.ADD_FLOW, T.ADD_BATTERY_B1, T.ADD_FLOW, T.ADD_FLOW);

   private static String out = "transformations/out.xmi";

   public static void main(final String[] args) {

      rewriteToUriPath(Paths.get("transformations", "trafochain.henshin"),
            "/transformationselection/metamodel/trafochainselection.ecore",
            "http://momot.big.tuwien.ac.at/examples/trafochainselection/1.0");
      TrafochainselectionPackage.eINSTANCE.getClass();
      final HenshinResourceSet resourceSet = new HenshinResourceSet();

      // Register relevant resource factories for model loading (XMIResourceFactoryImpl usually works):
      resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());

      // Load a model:
      final Resource model = resourceSet.getResource("problem/myNewModel.xmi");

      // Load the Henshin module:
      final org.eclipse.emf.henshin.model.Module module = resourceSet.getModule("transformations/trafochain.henshin");

      final Engine engine = new MomotEngine();
      // Initialize the graph:
      final EGraph graph = new EGraphImpl(model);
      final Random r = new Random();
      // Find the unit to be applied:
      // final Unit unit = module.getUnit("addBattery2");

      Unit unit = module.getUnit("startTransformation");
      UnitApplication application = new UnitApplicationImpl(engine, graph, unit, null);
      application.setParameterValue("chain", "chain1");
      application.setParameterValue("transformation", "KM32Table");
      if(!application.execute(null)) {
         System.out.println("-> Failed");
      }

      unit = module.getUnit("addTransformation");
      application = new UnitApplicationImpl(engine, graph, unit, null);
      application.setParameterValue("transformation", "Table2XML");
      if(!application.execute(null)) {
         System.out.println("-> Failed");
      }

      /*
       * for(final T t : ops) {
       * UnitApplication application = null;
       * Unit unit = null;
       * switch(t) {
       * case ADD_FLOW:
       * unit = module.getUnit("addFlow");
       * application = new UnitApplicationImpl(engine, graph, unit, null);
       * application.setParameterValue("id", r.nextInt());
       * break;
       * case ADD_BATTERY_B1:
       * unit = module.getUnit("addBlock");
       * application = new UnitApplicationImpl(engine, graph, unit, null);
       * application.setParameterValue("blockid", "b1");
       * application.setParameterValue("blockType", "battery");
       * break;
       * case ADD_BATTERY_B2:
       * unit = module.getUnit("addBlock");
       * application = new UnitApplicationImpl(engine, graph, unit, null);
       * application.setParameterValue("blockid", "b2");
       * application.setParameterValue("blockType", "battery");
       * break;
       * case ADD_PUMP_P1:
       * unit = module.getUnit("selectPump");
       * application = new UnitApplicationImpl(engine, graph, unit, null);
       * application.setParameterValue("id", "p1");
       * break;
       * case ADD_PUMP_P2:
       * unit = module.getUnit("selectPump");
       * application = new UnitApplicationImpl(engine, graph, unit, null);
       * application.setParameterValue("id", "p2");
       * break;
       * }
       * System.out.println(t);
       * if(!application.execute(null)) {
       * System.out.println("-> Failed");
       * }
       * }
       */

      if(out != null) {
         final HenshinResourceSet rSet = new HenshinResourceSet();
         rSet.getPackageRegistry().put(TrafochainselectionPackage.eNS_URI, TrafochainselectionPackage.eINSTANCE);

         final Resource oR = rSet.createResource(URI.createFileURI(out));
         oR.getContents().add(model.getContents().get(0));
         try {
            oR.save(null);
         } catch(final IOException e) {
            e.printStackTrace();
         }
      }

      // final UnitApplication application = new UnitApplicationImpl(engine, graph, unit, null);
      // application.setParameterValue("amount", 1);

      // application.setParameterValue("id", "b1");
      // application.setParameterValue("p2", object);

      /*
       * if(!application.execute(null)) {
       * throw new RuntimeException("Error");
       * }
       */

      System.out.println(model);
   }

   private static void rewriteToUriPath(final Path p, final String search, final String replace) {

      final Charset charset = StandardCharsets.UTF_8;

      String content = null;
      try {
         content = new String(Files.readAllBytes(p), charset);
      } catch(final IOException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
      content = content.replaceAll(search, replace);
      try {
         Files.write(p, content.getBytes(charset));
      } catch(final IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }

}
