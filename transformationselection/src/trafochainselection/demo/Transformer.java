package trafochainselection.demo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.tools.ant.util.ResourceUtils;
import org.eclipse.epsilon.common.parse.problem.ParseProblem;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.IEolModule;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;
import org.eclipse.epsilon.eol.models.Model;
import org.eclipse.epsilon.eol.models.ModelRepository;
import org.eclipse.epsilon.etl.EtlModule;

public class Transformer {

   final static ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

   /**
    * Converts provided model (location of xmi resource) to an EMF model object that inherently links the parsed model
    * to its meta-model.
    *
    * @param modelLocation
    *           location of a model resource (xmi file)
    * @param inputModel
    *           flag to indicate whether the provided model reference is an input- or output-model
    *           location.
    * @return
    */
   private static IModel convertToEmfModel(final String modelLocation, final String identifier, final String mmPath,
         final boolean inputModel) {

      try {
         return createEmfModel(identifier, modelLocation, mmPath, inputModel, !inputModel);
      } catch(EolModelLoadingException | URISyntaxException e) {
         throw new RuntimeException("Unable to create EMF model bundle object based on provided model location.");
      }
   }

   /**
    * Creates an EMF model (bundled object) which can be used by the ETL module performing the transformation. The
    * bundle houses the referenced model as an object object + metamodel object + identifier + persistence settings
    */
   private static EmfModel createEmfModel(final String name, final String model, final String metamodel,
         final boolean readOnLoad, final boolean storeOnDisposal) throws EolModelLoadingException, URISyntaxException {
      final EmfModel emfModel = new EmfModel();
      final StringProperties properties = new StringProperties();
      properties.put(Model.PROPERTY_NAME, name);
      properties.put(EmfModel.PROPERTY_FILE_BASED_METAMODEL_URI, URI.create(metamodel).toString());
      properties.put(EmfModel.PROPERTY_MODEL_URI, model);
      properties.put(Model.PROPERTY_READONLOAD, readOnLoad + "");
      properties.put(Model.PROPERTY_STOREONDISPOSAL, storeOnDisposal + "");
      emfModel.load(properties, (IRelativePathResolver) null);
      return emfModel;
   }

   private static URI getResourceURI(final Path relativeResourceLocation) throws URISyntaxException {

      Path root;
      if(relativeResourceLocation.startsWith("/")) {
         root = Paths.get(ResourceUtils.class.getResource("").toURI()).getRoot();
      } else {

         root = Paths.get(ResourceUtils.class.getResource("").toURI());
      }
      final URI targetUri = root.resolve(relativeResourceLocation).toAbsolutePath().toUri();
      return targetUri;
   }

   public static void transform(final Path transformationLocation, final String inputModelLocation,
         final String inputModelIdentifier, final String inputModelMM, final String outputModelLocation,
         final String outputModelIdentifier, final String outputModelMM) {

      final IEolModule module = new EtlModule();

      // Load and parse the corresponding ETL file.
      try {
         module.parse(transformationLocation.toUri());
      } catch(final Exception e) {
         throw new RuntimeException("Unable locate ETL module.");
      }

      // Abort in case there were parse errors.
      if(!module.getParseProblems().isEmpty()) {
         for(final ParseProblem problem : module.getParseProblems()) {
            throw new RuntimeException("Unable to parse provided ETL file: " + problem.getReason());
         }
      }
      // add the input / output models to this transformation module. (The models internally indicate whether they
      // are source / target models.)
      final IModel emfInputModel = convertToEmfModel(inputModelLocation, inputModelIdentifier, inputModelMM, true);
      final IModel emfOutputModel = convertToEmfModel(outputModelLocation, outputModelIdentifier, outputModelMM, false);
      final ModelRepository moduleModelRepository = module.getContext().getModelRepository();
      moduleModelRepository.addModel(emfInputModel);
      moduleModelRepository.addModel(emfOutputModel);
      module.getContext().setOutputStream(new PrintStream(outputStream));
      // Actually run the model transformation.
      try {
         module.execute();
      } catch(final EolRuntimeException e) {
         e.printStackTrace();
      }

      // Persist the transformation outcome on disk.
      module.getContext().getModelRepository().dispose();
   }

}
