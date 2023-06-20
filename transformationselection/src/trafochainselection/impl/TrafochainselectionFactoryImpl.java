/**
 */
package trafochainselection.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import trafochainselection.Metamodel;
import trafochainselection.ModelTransformationRepository;
import trafochainselection.TrafochainselectionFactory;
import trafochainselection.TrafochainselectionPackage;
import trafochainselection.Transformation;
import trafochainselection.TransformationChain;
import trafochainselection.TransformationModel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TrafochainselectionFactoryImpl extends EFactoryImpl implements TrafochainselectionFactory {
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @deprecated
    * @generated
    */
   @Deprecated
   public static TrafochainselectionPackage getPackage() {
      return TrafochainselectionPackage.eINSTANCE;
   }

   /**
    * Creates the default factory implementation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static TrafochainselectionFactory init() {
      try {
         TrafochainselectionFactory theTrafochainselectionFactory = (TrafochainselectionFactory)EPackage.Registry.INSTANCE.getEFactory(TrafochainselectionPackage.eNS_URI);
         if (theTrafochainselectionFactory != null) {
            return theTrafochainselectionFactory;
         }
      }
      catch (Exception exception) {
         EcorePlugin.INSTANCE.log(exception);
      }
      return new TrafochainselectionFactoryImpl();
   }

   /**
    * Creates an instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public TrafochainselectionFactoryImpl() {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EObject create(EClass eClass) {
      switch (eClass.getClassifierID()) {
         case TrafochainselectionPackage.MODEL_TRANSFORMATION_REPOSITORY: return createModelTransformationRepository();
         case TrafochainselectionPackage.TRANSFORMATION: return createTransformation();
         case TrafochainselectionPackage.METAMODEL: return createMetamodel();
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN: return createTransformationChain();
         case TrafochainselectionPackage.TRANSFORMATION_MODEL: return createTransformationModel();
         default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Metamodel createMetamodel() {
      MetamodelImpl metamodel = new MetamodelImpl();
      return metamodel;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public ModelTransformationRepository createModelTransformationRepository() {
      ModelTransformationRepositoryImpl modelTransformationRepository = new ModelTransformationRepositoryImpl();
      return modelTransformationRepository;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Transformation createTransformation() {
      TransformationImpl transformation = new TransformationImpl();
      return transformation;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public TransformationChain createTransformationChain() {
      TransformationChainImpl transformationChain = new TransformationChainImpl();
      return transformationChain;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public TransformationModel createTransformationModel() {
      TransformationModelImpl transformationModel = new TransformationModelImpl();
      return transformationModel;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public TrafochainselectionPackage getTrafochainselectionPackage() {
      return (TrafochainselectionPackage)getEPackage();
   }

} // TrafochainselectionFactoryImpl
