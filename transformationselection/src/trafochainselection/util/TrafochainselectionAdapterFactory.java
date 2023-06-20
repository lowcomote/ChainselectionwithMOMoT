/**
 */
package trafochainselection.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import trafochainselection.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see trafochainselection.TrafochainselectionPackage
 * @generated
 */
public class TrafochainselectionAdapterFactory extends AdapterFactoryImpl {
	/**
    * The cached model package.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected static TrafochainselectionPackage modelPackage;

	/**
    * Creates an instance of the adapter factory.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public TrafochainselectionAdapterFactory() {
      if (modelPackage == null) {
         modelPackage = TrafochainselectionPackage.eINSTANCE;
      }
   }

	/**
    * Returns whether this factory is applicable for the type of the object.
    * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
    * @return whether this factory is applicable for the type of the object.
    * @generated
    */
	@Override
	public boolean isFactoryForType(Object object) {
      if (object == modelPackage) {
         return true;
      }
      if (object instanceof EObject) {
         return ((EObject)object).eClass().getEPackage() == modelPackage;
      }
      return false;
   }

	/**
    * The switch that delegates to the <code>createXXX</code> methods.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected TrafochainselectionSwitch<Adapter> modelSwitch =
		new TrafochainselectionSwitch<Adapter>() {
         @Override
         public Adapter caseModelTransformationRepository(ModelTransformationRepository object) {
            return createModelTransformationRepositoryAdapter();
         }
         @Override
         public Adapter caseTransformation(Transformation object) {
            return createTransformationAdapter();
         }
         @Override
         public Adapter caseMetamodel(Metamodel object) {
            return createMetamodelAdapter();
         }
         @Override
         public Adapter caseTransformationChain(TransformationChain object) {
            return createTransformationChainAdapter();
         }
         @Override
         public Adapter caseTransformationModel(TransformationModel object) {
            return createTransformationModelAdapter();
         }
         @Override
         public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
         }
      };

	/**
    * Creates an adapter for the <code>target</code>.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param target the object to adapt.
    * @return the adapter for the <code>target</code>.
    * @generated
    */
	@Override
	public Adapter createAdapter(Notifier target) {
      return modelSwitch.doSwitch((EObject)target);
   }


	/**
    * Creates a new adapter for an object of class '{@link trafochainselection.ModelTransformationRepository <em>Model Transformation Repository</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see trafochainselection.ModelTransformationRepository
    * @generated
    */
	public Adapter createModelTransformationRepositoryAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link trafochainselection.Transformation <em>Transformation</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see trafochainselection.Transformation
    * @generated
    */
	public Adapter createTransformationAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link trafochainselection.Metamodel <em>Metamodel</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see trafochainselection.Metamodel
    * @generated
    */
	public Adapter createMetamodelAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link trafochainselection.TransformationChain <em>Transformation Chain</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see trafochainselection.TransformationChain
    * @generated
    */
	public Adapter createTransformationChainAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link trafochainselection.TransformationModel <em>Transformation Model</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see trafochainselection.TransformationModel
    * @generated
    */
	public Adapter createTransformationModelAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for the default case.
    * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @generated
    */
	public Adapter createEObjectAdapter() {
      return null;
   }

} //TrafochainselectionAdapterFactory
