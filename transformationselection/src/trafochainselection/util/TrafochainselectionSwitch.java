/**
 */
package trafochainselection.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import trafochainselection.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see trafochainselection.TrafochainselectionPackage
 * @generated
 */
public class TrafochainselectionSwitch<T> extends Switch<T> {
	/**
    * The cached model package
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected static TrafochainselectionPackage modelPackage;

	/**
    * Creates an instance of the switch.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public TrafochainselectionSwitch() {
      if (modelPackage == null) {
         modelPackage = TrafochainselectionPackage.eINSTANCE;
      }
   }

	/**
    * Checks whether this is a switch for the given package.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param ePackage the package in question.
    * @return whether this is a switch for the given package.
    * @generated
    */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
      return ePackage == modelPackage;
   }

	/**
    * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the first non-null result returned by a <code>caseXXX</code> call.
    * @generated
    */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
      switch (classifierID) {
         case TrafochainselectionPackage.MODEL_TRANSFORMATION_REPOSITORY: {
            ModelTransformationRepository modelTransformationRepository = (ModelTransformationRepository)theEObject;
            T result = caseModelTransformationRepository(modelTransformationRepository);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case TrafochainselectionPackage.TRANSFORMATION: {
            Transformation transformation = (Transformation)theEObject;
            T result = caseTransformation(transformation);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case TrafochainselectionPackage.METAMODEL: {
            Metamodel metamodel = (Metamodel)theEObject;
            T result = caseMetamodel(metamodel);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN: {
            TransformationChain transformationChain = (TransformationChain)theEObject;
            T result = caseTransformationChain(transformationChain);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case TrafochainselectionPackage.TRANSFORMATION_MODEL: {
            TransformationModel transformationModel = (TransformationModel)theEObject;
            T result = caseTransformationModel(transformationModel);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         default: return defaultCase(theEObject);
      }
   }

	/**
    * Returns the result of interpreting the object as an instance of '<em>Model Transformation Repository</em>'.
    * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Model Transformation Repository</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
	public T caseModelTransformationRepository(ModelTransformationRepository object) {
      return null;
   }

	/**
    * Returns the result of interpreting the object as an instance of '<em>Transformation</em>'.
    * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Transformation</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
	public T caseTransformation(Transformation object) {
      return null;
   }

	/**
    * Returns the result of interpreting the object as an instance of '<em>Metamodel</em>'.
    * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Metamodel</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
	public T caseMetamodel(Metamodel object) {
      return null;
   }

	/**
    * Returns the result of interpreting the object as an instance of '<em>Transformation Chain</em>'.
    * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Transformation Chain</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
	public T caseTransformationChain(TransformationChain object) {
      return null;
   }

	/**
    * Returns the result of interpreting the object as an instance of '<em>Transformation Model</em>'.
    * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Transformation Model</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
	public T caseTransformationModel(TransformationModel object) {
      return null;
   }

	/**
    * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
    * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject)
    * @generated
    */
	@Override
	public T defaultCase(EObject object) {
      return null;
   }

} //TrafochainselectionSwitch
