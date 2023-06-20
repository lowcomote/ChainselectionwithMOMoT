/**
 */
package trafochainselection;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transformation Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link trafochainselection.TransformationModel#getTransformationchain <em>Transformationchain</em>}</li>
 *   <li>{@link trafochainselection.TransformationModel#getModelTransformationRepository <em>Model Transformation Repository</em>}</li>
 * </ul>
 *
 * @see trafochainselection.TrafochainselectionPackage#getTransformationModel()
 * @model
 * @generated
 */
public interface TransformationModel extends EObject {
	/**
    * Returns the value of the '<em><b>Transformationchain</b></em>' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Transformationchain</em>' containment reference.
    * @see #setTransformationchain(TransformationChain)
    * @see trafochainselection.TrafochainselectionPackage#getTransformationModel_Transformationchain()
    * @model containment="true"
    * @generated
    */
	TransformationChain getTransformationchain();

	/**
    * Sets the value of the '{@link trafochainselection.TransformationModel#getTransformationchain <em>Transformationchain</em>}' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Transformationchain</em>' containment reference.
    * @see #getTransformationchain()
    * @generated
    */
	void setTransformationchain(TransformationChain value);

	/**
    * Returns the value of the '<em><b>Model Transformation Repository</b></em>' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Model Transformation Repository</em>' containment reference.
    * @see #setModelTransformationRepository(ModelTransformationRepository)
    * @see trafochainselection.TrafochainselectionPackage#getTransformationModel_ModelTransformationRepository()
    * @model containment="true"
    * @generated
    */
	ModelTransformationRepository getModelTransformationRepository();

	/**
    * Sets the value of the '{@link trafochainselection.TransformationModel#getModelTransformationRepository <em>Model Transformation Repository</em>}' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Model Transformation Repository</em>' containment reference.
    * @see #getModelTransformationRepository()
    * @generated
    */
	void setModelTransformationRepository(ModelTransformationRepository value);

} // TransformationModel
