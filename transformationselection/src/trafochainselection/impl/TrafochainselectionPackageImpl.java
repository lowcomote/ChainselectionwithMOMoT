/**
 */
package trafochainselection.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import trafochainselection.Metamodel;
import trafochainselection.ModelTransformationRepository;
import trafochainselection.TrafochainselectionFactory;
import trafochainselection.TrafochainselectionPackage;
import trafochainselection.Transformation;
import trafochainselection.TransformationChain;
import trafochainselection.TransformationModel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TrafochainselectionPackageImpl extends EPackageImpl implements TrafochainselectionPackage {
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass modelTransformationRepositoryEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass transformationEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass metamodelEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass transformationChainEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass transformationModelEClass = null;

   /**
    * Creates an instance of the model <b>Package</b>, registered with
    * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
    * package URI value.
    * <p>Note: the correct way to create the package is via the static
    * factory method {@link #init init()}, which also performs
    * initialization of the package, or returns the registered package,
    * if one already exists.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.emf.ecore.EPackage.Registry
    * @see trafochainselection.TrafochainselectionPackage#eNS_URI
    * @see #init()
    * @generated
    */
   private TrafochainselectionPackageImpl() {
      super(eNS_URI, TrafochainselectionFactory.eINSTANCE);
   }
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private static boolean isInited = false;

   /**
    * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
    *
    * <p>This method is used to initialize {@link TrafochainselectionPackage#eINSTANCE} when that field is accessed.
    * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #eNS_URI
    * @see #createPackageContents()
    * @see #initializePackageContents()
    * @generated
    */
   public static TrafochainselectionPackage init() {
      if (isInited) return (TrafochainselectionPackage)EPackage.Registry.INSTANCE.getEPackage(TrafochainselectionPackage.eNS_URI);

      // Obtain or create and register package
      Object registeredTrafochainselectionPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
      TrafochainselectionPackageImpl theTrafochainselectionPackage = registeredTrafochainselectionPackage instanceof TrafochainselectionPackageImpl ? (TrafochainselectionPackageImpl)registeredTrafochainselectionPackage : new TrafochainselectionPackageImpl();

      isInited = true;

      // Create package meta-data objects
      theTrafochainselectionPackage.createPackageContents();

      // Initialize created meta-data
      theTrafochainselectionPackage.initializePackageContents();

      // Mark meta-data to indicate it can't be changed
      theTrafochainselectionPackage.freeze();

      // Update the registry and return the package
      EPackage.Registry.INSTANCE.put(TrafochainselectionPackage.eNS_URI, theTrafochainselectionPackage);
      return theTrafochainselectionPackage;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EClass getModelTransformationRepository() {
      return modelTransformationRepositoryEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EReference getModelTransformationRepository_Transformations() {
      return (EReference)modelTransformationRepositoryEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EReference getModelTransformationRepository_Metamodels() {
      return (EReference)modelTransformationRepositoryEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EClass getTransformation() {
      return transformationEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EAttribute getTransformation_Id() {
      return (EAttribute)transformationEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EReference getTransformation_Src() {
      return (EReference)transformationEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EReference getTransformation_Target() {
      return (EReference)transformationEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EAttribute getTransformation_Complexity() {
      return (EAttribute)transformationEClass.getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EAttribute getTransformation_Coverage() {
      return (EAttribute)transformationEClass.getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EClass getMetamodel() {
      return metamodelEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EAttribute getMetamodel_Id() {
      return (EAttribute)metamodelEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EClass getTransformationChain() {
      return transformationChainEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EAttribute getTransformationChain_Id() {
      return (EAttribute)transformationChainEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EAttribute getTransformationChain_InputModel() {
      return (EAttribute)transformationChainEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EReference getTransformationChain_Start() {
      return (EReference)transformationChainEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EReference getTransformationChain_Final() {
      return (EReference)transformationChainEClass.getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EReference getTransformationChain_Uses() {
      return (EReference)transformationChainEClass.getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EReference getTransformationChain_OutputMM() {
      return (EReference)transformationChainEClass.getEStructuralFeatures().get(5);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EClass getTransformationModel() {
      return transformationModelEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EReference getTransformationModel_Transformationchain() {
      return (EReference)transformationModelEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EReference getTransformationModel_ModelTransformationRepository() {
      return (EReference)transformationModelEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public TrafochainselectionFactory getTrafochainselectionFactory() {
      return (TrafochainselectionFactory)getEFactoryInstance();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private boolean isCreated = false;

   /**
    * Creates the meta-model objects for the package.  This method is
    * guarded to have no affect on any invocation but its first.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void createPackageContents() {
      if (isCreated) return;
      isCreated = true;

      // Create classes and their features
      modelTransformationRepositoryEClass = createEClass(MODEL_TRANSFORMATION_REPOSITORY);
      createEReference(modelTransformationRepositoryEClass, MODEL_TRANSFORMATION_REPOSITORY__TRANSFORMATIONS);
      createEReference(modelTransformationRepositoryEClass, MODEL_TRANSFORMATION_REPOSITORY__METAMODELS);

      transformationEClass = createEClass(TRANSFORMATION);
      createEAttribute(transformationEClass, TRANSFORMATION__ID);
      createEReference(transformationEClass, TRANSFORMATION__SRC);
      createEReference(transformationEClass, TRANSFORMATION__TARGET);
      createEAttribute(transformationEClass, TRANSFORMATION__COMPLEXITY);
      createEAttribute(transformationEClass, TRANSFORMATION__COVERAGE);

      metamodelEClass = createEClass(METAMODEL);
      createEAttribute(metamodelEClass, METAMODEL__ID);

      transformationChainEClass = createEClass(TRANSFORMATION_CHAIN);
      createEAttribute(transformationChainEClass, TRANSFORMATION_CHAIN__ID);
      createEAttribute(transformationChainEClass, TRANSFORMATION_CHAIN__INPUT_MODEL);
      createEReference(transformationChainEClass, TRANSFORMATION_CHAIN__START);
      createEReference(transformationChainEClass, TRANSFORMATION_CHAIN__FINAL);
      createEReference(transformationChainEClass, TRANSFORMATION_CHAIN__USES);
      createEReference(transformationChainEClass, TRANSFORMATION_CHAIN__OUTPUT_MM);

      transformationModelEClass = createEClass(TRANSFORMATION_MODEL);
      createEReference(transformationModelEClass, TRANSFORMATION_MODEL__TRANSFORMATIONCHAIN);
      createEReference(transformationModelEClass, TRANSFORMATION_MODEL__MODEL_TRANSFORMATION_REPOSITORY);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private boolean isInitialized = false;

   /**
    * Complete the initialization of the package and its meta-model.  This
    * method is guarded to have no affect on any invocation but its first.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void initializePackageContents() {
      if (isInitialized) return;
      isInitialized = true;

      // Initialize package
      setName(eNAME);
      setNsPrefix(eNS_PREFIX);
      setNsURI(eNS_URI);

      // Create type parameters

      // Set bounds for type parameters

      // Add supertypes to classes

      // Initialize classes, features, and operations; add parameters
      initEClass(modelTransformationRepositoryEClass, ModelTransformationRepository.class, "ModelTransformationRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getModelTransformationRepository_Transformations(), this.getTransformation(), null, "transformations", null, 0, -1, ModelTransformationRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getModelTransformationRepository_Metamodels(), this.getMetamodel(), null, "metamodels", null, 0, -1, ModelTransformationRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(transformationEClass, Transformation.class, "Transformation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getTransformation_Id(), ecorePackage.getEString(), "id", null, 1, 1, Transformation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getTransformation_Src(), this.getMetamodel(), null, "src", null, 1, 1, Transformation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getTransformation_Target(), this.getMetamodel(), null, "target", null, 1, 1, Transformation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getTransformation_Complexity(), ecorePackage.getEInt(), "complexity", null, 0, 1, Transformation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getTransformation_Coverage(), ecorePackage.getEDouble(), "coverage", null, 0, 1, Transformation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(metamodelEClass, Metamodel.class, "Metamodel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getMetamodel_Id(), ecorePackage.getEString(), "id", null, 1, 1, Metamodel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(transformationChainEClass, TransformationChain.class, "TransformationChain", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getTransformationChain_Id(), ecorePackage.getEString(), "id", null, 1, 1, TransformationChain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getTransformationChain_InputModel(), ecorePackage.getEString(), "inputModel", null, 1, 1, TransformationChain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getTransformationChain_Start(), this.getMetamodel(), null, "start", null, 1, 1, TransformationChain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getTransformationChain_Final(), this.getMetamodel(), null, "final", null, 1, 1, TransformationChain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getTransformationChain_Uses(), this.getTransformation(), null, "uses", null, 0, -1, TransformationChain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getTransformationChain_OutputMM(), this.getMetamodel(), null, "outputMM", null, 0, 1, TransformationChain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(transformationModelEClass, TransformationModel.class, "TransformationModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getTransformationModel_Transformationchain(), this.getTransformationChain(), null, "transformationchain", null, 0, 1, TransformationModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getTransformationModel_ModelTransformationRepository(), this.getModelTransformationRepository(), null, "modelTransformationRepository", null, 0, 1, TransformationModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      // Create resource
      createResource(eNS_URI);
   }

} //TrafochainselectionPackageImpl
