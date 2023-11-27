/**
 */
package trafochainselection;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see trafochainselection.TrafochainselectionFactory
 * @model kind="package"
 * @generated
 */
public interface TrafochainselectionPackage extends EPackage {
   /**
    * The package name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNAME = "trafochainselection";

   /**
    * The package namespace URI.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_URI = "http://momot.big.tuwien.ac.at/examples/trafochainselection/1.0";

   /**
    * The package namespace name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_PREFIX = "trafochainselection";

   /**
    * The singleton instance of the package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   TrafochainselectionPackage eINSTANCE = trafochainselection.impl.TrafochainselectionPackageImpl.init();

   /**
    * The meta object id for the '{@link trafochainselection.impl.ModelTransformationRepositoryImpl <em>Model Transformation Repository</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see trafochainselection.impl.ModelTransformationRepositoryImpl
    * @see trafochainselection.impl.TrafochainselectionPackageImpl#getModelTransformationRepository()
    * @generated
    */
   int MODEL_TRANSFORMATION_REPOSITORY = 0;

   /**
    * The feature id for the '<em><b>Transformations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int MODEL_TRANSFORMATION_REPOSITORY__TRANSFORMATIONS = 0;

   /**
    * The feature id for the '<em><b>Metamodels</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int MODEL_TRANSFORMATION_REPOSITORY__METAMODELS = 1;

   /**
    * The number of structural features of the '<em>Model Transformation Repository</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int MODEL_TRANSFORMATION_REPOSITORY_FEATURE_COUNT = 2;

   /**
    * The number of operations of the '<em>Model Transformation Repository</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int MODEL_TRANSFORMATION_REPOSITORY_OPERATION_COUNT = 0;

   /**
    * The meta object id for the '{@link trafochainselection.impl.TransformationImpl <em>Transformation</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see trafochainselection.impl.TransformationImpl
    * @see trafochainselection.impl.TrafochainselectionPackageImpl#getTransformation()
    * @generated
    */
   int TRANSFORMATION = 1;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TRANSFORMATION__ID = 0;

   /**
    * The feature id for the '<em><b>Src</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TRANSFORMATION__SRC = 1;

   /**
    * The feature id for the '<em><b>Target</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TRANSFORMATION__TARGET = 2;

   /**
    * The feature id for the '<em><b>Complexity</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TRANSFORMATION__COMPLEXITY = 3;

   /**
    * The feature id for the '<em><b>Coverage</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TRANSFORMATION__COVERAGE = 4;

   /**
    * The number of structural features of the '<em>Transformation</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TRANSFORMATION_FEATURE_COUNT = 5;

   /**
    * The number of operations of the '<em>Transformation</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TRANSFORMATION_OPERATION_COUNT = 0;

   /**
    * The meta object id for the '{@link trafochainselection.impl.MetamodelImpl <em>Metamodel</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see trafochainselection.impl.MetamodelImpl
    * @see trafochainselection.impl.TrafochainselectionPackageImpl#getMetamodel()
    * @generated
    */
   int METAMODEL = 2;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int METAMODEL__ID = 0;

   /**
    * The number of structural features of the '<em>Metamodel</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int METAMODEL_FEATURE_COUNT = 1;

   /**
    * The number of operations of the '<em>Metamodel</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int METAMODEL_OPERATION_COUNT = 0;

   /**
    * The meta object id for the '{@link trafochainselection.impl.TransformationChainImpl <em>Transformation Chain</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see trafochainselection.impl.TransformationChainImpl
    * @see trafochainselection.impl.TrafochainselectionPackageImpl#getTransformationChain()
    * @generated
    */
   int TRANSFORMATION_CHAIN = 3;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TRANSFORMATION_CHAIN__ID = 0;

   /**
    * The feature id for the '<em><b>Input Model</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TRANSFORMATION_CHAIN__INPUT_MODEL = 1;

   /**
    * The feature id for the '<em><b>Start</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TRANSFORMATION_CHAIN__START = 2;

   /**
    * The feature id for the '<em><b>Final</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TRANSFORMATION_CHAIN__FINAL = 3;

   /**
    * The feature id for the '<em><b>Uses</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TRANSFORMATION_CHAIN__USES = 4;

   /**
    * The feature id for the '<em><b>Output MM</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TRANSFORMATION_CHAIN__OUTPUT_MM = 5;

   /**
    * The number of structural features of the '<em>Transformation Chain</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TRANSFORMATION_CHAIN_FEATURE_COUNT = 6;

   /**
    * The number of operations of the '<em>Transformation Chain</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TRANSFORMATION_CHAIN_OPERATION_COUNT = 0;

   /**
    * The meta object id for the '{@link trafochainselection.impl.TransformationModelImpl <em>Transformation Model</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see trafochainselection.impl.TransformationModelImpl
    * @see trafochainselection.impl.TrafochainselectionPackageImpl#getTransformationModel()
    * @generated
    */
   int TRANSFORMATION_MODEL = 4;

   /**
    * The feature id for the '<em><b>Transformationchain</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TRANSFORMATION_MODEL__TRANSFORMATIONCHAIN = 0;

   /**
    * The feature id for the '<em><b>Model Transformation Repository</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TRANSFORMATION_MODEL__MODEL_TRANSFORMATION_REPOSITORY = 1;

   /**
    * The number of structural features of the '<em>Transformation Model</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TRANSFORMATION_MODEL_FEATURE_COUNT = 2;

   /**
    * The number of operations of the '<em>Transformation Model</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TRANSFORMATION_MODEL_OPERATION_COUNT = 0;


   /**
    * Returns the meta object for class '{@link trafochainselection.ModelTransformationRepository <em>Model Transformation Repository</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Model Transformation Repository</em>'.
    * @see trafochainselection.ModelTransformationRepository
    * @generated
    */
   EClass getModelTransformationRepository();

   /**
    * Returns the meta object for the containment reference list '{@link trafochainselection.ModelTransformationRepository#getTransformations <em>Transformations</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Transformations</em>'.
    * @see trafochainselection.ModelTransformationRepository#getTransformations()
    * @see #getModelTransformationRepository()
    * @generated
    */
   EReference getModelTransformationRepository_Transformations();

   /**
    * Returns the meta object for the containment reference list '{@link trafochainselection.ModelTransformationRepository#getMetamodels <em>Metamodels</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Metamodels</em>'.
    * @see trafochainselection.ModelTransformationRepository#getMetamodels()
    * @see #getModelTransformationRepository()
    * @generated
    */
   EReference getModelTransformationRepository_Metamodels();

   /**
    * Returns the meta object for class '{@link trafochainselection.Transformation <em>Transformation</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Transformation</em>'.
    * @see trafochainselection.Transformation
    * @generated
    */
   EClass getTransformation();

   /**
    * Returns the meta object for the attribute '{@link trafochainselection.Transformation#getId <em>Id</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Id</em>'.
    * @see trafochainselection.Transformation#getId()
    * @see #getTransformation()
    * @generated
    */
   EAttribute getTransformation_Id();

   /**
    * Returns the meta object for the reference '{@link trafochainselection.Transformation#getSrc <em>Src</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Src</em>'.
    * @see trafochainselection.Transformation#getSrc()
    * @see #getTransformation()
    * @generated
    */
   EReference getTransformation_Src();

   /**
    * Returns the meta object for the reference '{@link trafochainselection.Transformation#getTarget <em>Target</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Target</em>'.
    * @see trafochainselection.Transformation#getTarget()
    * @see #getTransformation()
    * @generated
    */
   EReference getTransformation_Target();

   /**
    * Returns the meta object for the attribute '{@link trafochainselection.Transformation#getComplexity <em>Complexity</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Complexity</em>'.
    * @see trafochainselection.Transformation#getComplexity()
    * @see #getTransformation()
    * @generated
    */
   EAttribute getTransformation_Complexity();

   /**
    * Returns the meta object for the attribute '{@link trafochainselection.Transformation#getCoverage <em>Coverage</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Coverage</em>'.
    * @see trafochainselection.Transformation#getCoverage()
    * @see #getTransformation()
    * @generated
    */
   EAttribute getTransformation_Coverage();

   /**
    * Returns the meta object for class '{@link trafochainselection.Metamodel <em>Metamodel</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Metamodel</em>'.
    * @see trafochainselection.Metamodel
    * @generated
    */
   EClass getMetamodel();

   /**
    * Returns the meta object for the attribute '{@link trafochainselection.Metamodel#getId <em>Id</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Id</em>'.
    * @see trafochainselection.Metamodel#getId()
    * @see #getMetamodel()
    * @generated
    */
   EAttribute getMetamodel_Id();

   /**
    * Returns the meta object for class '{@link trafochainselection.TransformationChain <em>Transformation Chain</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Transformation Chain</em>'.
    * @see trafochainselection.TransformationChain
    * @generated
    */
   EClass getTransformationChain();

   /**
    * Returns the meta object for the attribute '{@link trafochainselection.TransformationChain#getId <em>Id</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Id</em>'.
    * @see trafochainselection.TransformationChain#getId()
    * @see #getTransformationChain()
    * @generated
    */
   EAttribute getTransformationChain_Id();

   /**
    * Returns the meta object for the attribute '{@link trafochainselection.TransformationChain#getInputModel <em>Input Model</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Input Model</em>'.
    * @see trafochainselection.TransformationChain#getInputModel()
    * @see #getTransformationChain()
    * @generated
    */
   EAttribute getTransformationChain_InputModel();

   /**
    * Returns the meta object for the reference '{@link trafochainselection.TransformationChain#getStart <em>Start</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Start</em>'.
    * @see trafochainselection.TransformationChain#getStart()
    * @see #getTransformationChain()
    * @generated
    */
   EReference getTransformationChain_Start();

   /**
    * Returns the meta object for the reference '{@link trafochainselection.TransformationChain#getFinal <em>Final</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Final</em>'.
    * @see trafochainselection.TransformationChain#getFinal()
    * @see #getTransformationChain()
    * @generated
    */
   EReference getTransformationChain_Final();

   /**
    * Returns the meta object for the reference list '{@link trafochainselection.TransformationChain#getUses <em>Uses</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference list '<em>Uses</em>'.
    * @see trafochainselection.TransformationChain#getUses()
    * @see #getTransformationChain()
    * @generated
    */
   EReference getTransformationChain_Uses();

   /**
    * Returns the meta object for the reference '{@link trafochainselection.TransformationChain#getOutputMM <em>Output MM</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Output MM</em>'.
    * @see trafochainselection.TransformationChain#getOutputMM()
    * @see #getTransformationChain()
    * @generated
    */
   EReference getTransformationChain_OutputMM();

   /**
    * Returns the meta object for class '{@link trafochainselection.TransformationModel <em>Transformation Model</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Transformation Model</em>'.
    * @see trafochainselection.TransformationModel
    * @generated
    */
   EClass getTransformationModel();

   /**
    * Returns the meta object for the containment reference '{@link trafochainselection.TransformationModel#getTransformationchain <em>Transformationchain</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Transformationchain</em>'.
    * @see trafochainselection.TransformationModel#getTransformationchain()
    * @see #getTransformationModel()
    * @generated
    */
   EReference getTransformationModel_Transformationchain();

   /**
    * Returns the meta object for the containment reference '{@link trafochainselection.TransformationModel#getModelTransformationRepository <em>Model Transformation Repository</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Model Transformation Repository</em>'.
    * @see trafochainselection.TransformationModel#getModelTransformationRepository()
    * @see #getTransformationModel()
    * @generated
    */
   EReference getTransformationModel_ModelTransformationRepository();

   /**
    * Returns the factory that creates the instances of the model.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the factory that creates the instances of the model.
    * @generated
    */
   TrafochainselectionFactory getTrafochainselectionFactory();

   /**
    * <!-- begin-user-doc -->
    * Defines literals for the meta objects that represent
    * <ul>
    *   <li>each class,</li>
    *   <li>each feature of each class,</li>
    *   <li>each operation of each class,</li>
    *   <li>each enum,</li>
    *   <li>and each data type</li>
    * </ul>
    * <!-- end-user-doc -->
    * @generated
    */
   interface Literals {
      /**
       * The meta object literal for the '{@link trafochainselection.impl.ModelTransformationRepositoryImpl <em>Model Transformation Repository</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see trafochainselection.impl.ModelTransformationRepositoryImpl
       * @see trafochainselection.impl.TrafochainselectionPackageImpl#getModelTransformationRepository()
       * @generated
       */
      EClass MODEL_TRANSFORMATION_REPOSITORY = eINSTANCE.getModelTransformationRepository();

      /**
       * The meta object literal for the '<em><b>Transformations</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference MODEL_TRANSFORMATION_REPOSITORY__TRANSFORMATIONS = eINSTANCE.getModelTransformationRepository_Transformations();

      /**
       * The meta object literal for the '<em><b>Metamodels</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference MODEL_TRANSFORMATION_REPOSITORY__METAMODELS = eINSTANCE.getModelTransformationRepository_Metamodels();

      /**
       * The meta object literal for the '{@link trafochainselection.impl.TransformationImpl <em>Transformation</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see trafochainselection.impl.TransformationImpl
       * @see trafochainselection.impl.TrafochainselectionPackageImpl#getTransformation()
       * @generated
       */
      EClass TRANSFORMATION = eINSTANCE.getTransformation();

      /**
       * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute TRANSFORMATION__ID = eINSTANCE.getTransformation_Id();

      /**
       * The meta object literal for the '<em><b>Src</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference TRANSFORMATION__SRC = eINSTANCE.getTransformation_Src();

      /**
       * The meta object literal for the '<em><b>Target</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference TRANSFORMATION__TARGET = eINSTANCE.getTransformation_Target();

      /**
       * The meta object literal for the '<em><b>Complexity</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute TRANSFORMATION__COMPLEXITY = eINSTANCE.getTransformation_Complexity();

      /**
       * The meta object literal for the '<em><b>Coverage</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute TRANSFORMATION__COVERAGE = eINSTANCE.getTransformation_Coverage();

      /**
       * The meta object literal for the '{@link trafochainselection.impl.MetamodelImpl <em>Metamodel</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see trafochainselection.impl.MetamodelImpl
       * @see trafochainselection.impl.TrafochainselectionPackageImpl#getMetamodel()
       * @generated
       */
      EClass METAMODEL = eINSTANCE.getMetamodel();

      /**
       * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute METAMODEL__ID = eINSTANCE.getMetamodel_Id();

      /**
       * The meta object literal for the '{@link trafochainselection.impl.TransformationChainImpl <em>Transformation Chain</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see trafochainselection.impl.TransformationChainImpl
       * @see trafochainselection.impl.TrafochainselectionPackageImpl#getTransformationChain()
       * @generated
       */
      EClass TRANSFORMATION_CHAIN = eINSTANCE.getTransformationChain();

      /**
       * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute TRANSFORMATION_CHAIN__ID = eINSTANCE.getTransformationChain_Id();

      /**
       * The meta object literal for the '<em><b>Input Model</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute TRANSFORMATION_CHAIN__INPUT_MODEL = eINSTANCE.getTransformationChain_InputModel();

      /**
       * The meta object literal for the '<em><b>Start</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference TRANSFORMATION_CHAIN__START = eINSTANCE.getTransformationChain_Start();

      /**
       * The meta object literal for the '<em><b>Final</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference TRANSFORMATION_CHAIN__FINAL = eINSTANCE.getTransformationChain_Final();

      /**
       * The meta object literal for the '<em><b>Uses</b></em>' reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference TRANSFORMATION_CHAIN__USES = eINSTANCE.getTransformationChain_Uses();

      /**
       * The meta object literal for the '<em><b>Output MM</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference TRANSFORMATION_CHAIN__OUTPUT_MM = eINSTANCE.getTransformationChain_OutputMM();

      /**
       * The meta object literal for the '{@link trafochainselection.impl.TransformationModelImpl <em>Transformation Model</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see trafochainselection.impl.TransformationModelImpl
       * @see trafochainselection.impl.TrafochainselectionPackageImpl#getTransformationModel()
       * @generated
       */
      EClass TRANSFORMATION_MODEL = eINSTANCE.getTransformationModel();

      /**
       * The meta object literal for the '<em><b>Transformationchain</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference TRANSFORMATION_MODEL__TRANSFORMATIONCHAIN = eINSTANCE.getTransformationModel_Transformationchain();

      /**
       * The meta object literal for the '<em><b>Model Transformation Repository</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference TRANSFORMATION_MODEL__MODEL_TRANSFORMATION_REPOSITORY = eINSTANCE.getTransformationModel_ModelTransformationRepository();

   }

} //TrafochainselectionPackage
