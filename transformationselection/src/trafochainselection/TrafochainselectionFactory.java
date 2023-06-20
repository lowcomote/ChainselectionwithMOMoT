/**
 */
package trafochainselection;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see trafochainselection.TrafochainselectionPackage
 * @generated
 */
public interface TrafochainselectionFactory extends EFactory {
	/**
    * The singleton instance of the factory.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	TrafochainselectionFactory eINSTANCE = trafochainselection.impl.TrafochainselectionFactoryImpl.init();

	/**
    * Returns a new object of class '<em>Model Transformation Repository</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>Model Transformation Repository</em>'.
    * @generated
    */
	ModelTransformationRepository createModelTransformationRepository();

	/**
    * Returns a new object of class '<em>Transformation</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>Transformation</em>'.
    * @generated
    */
	Transformation createTransformation();

	/**
    * Returns a new object of class '<em>Metamodel</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>Metamodel</em>'.
    * @generated
    */
	Metamodel createMetamodel();

	/**
    * Returns a new object of class '<em>Transformation Chain</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>Transformation Chain</em>'.
    * @generated
    */
	TransformationChain createTransformationChain();

	/**
    * Returns a new object of class '<em>Transformation Model</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>Transformation Model</em>'.
    * @generated
    */
	TransformationModel createTransformationModel();

	/**
    * Returns the package supported by this factory.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the package supported by this factory.
    * @generated
    */
	TrafochainselectionPackage getTrafochainselectionPackage();

} //TrafochainselectionFactory
