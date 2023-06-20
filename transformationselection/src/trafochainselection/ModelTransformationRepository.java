/**
 */
package trafochainselection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Transformation Repository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link trafochainselection.ModelTransformationRepository#getTransformations <em>Transformations</em>}</li>
 *   <li>{@link trafochainselection.ModelTransformationRepository#getMetamodels <em>Metamodels</em>}</li>
 * </ul>
 *
 * @see trafochainselection.TrafochainselectionPackage#getModelTransformationRepository()
 * @model
 * @generated
 */
public interface ModelTransformationRepository extends EObject {
	/**
    * Returns the value of the '<em><b>Transformations</b></em>' containment reference list.
    * The list contents are of type {@link trafochainselection.Transformation}.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Transformations</em>' containment reference list.
    * @see trafochainselection.TrafochainselectionPackage#getModelTransformationRepository_Transformations()
    * @model containment="true"
    * @generated
    */
	EList<Transformation> getTransformations();

	/**
    * Returns the value of the '<em><b>Metamodels</b></em>' containment reference list.
    * The list contents are of type {@link trafochainselection.Metamodel}.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Metamodels</em>' containment reference list.
    * @see trafochainselection.TrafochainselectionPackage#getModelTransformationRepository_Metamodels()
    * @model containment="true"
    * @generated
    */
	EList<Metamodel> getMetamodels();

} // ModelTransformationRepository
