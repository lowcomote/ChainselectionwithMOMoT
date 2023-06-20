/**
 */
package trafochainselection;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Metamodel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link trafochainselection.Metamodel#getId <em>Id</em>}</li>
 * </ul>
 *
 * @see trafochainselection.TrafochainselectionPackage#getMetamodel()
 * @model
 * @generated
 */
public interface Metamodel extends EObject {
	/**
    * Returns the value of the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Id</em>' attribute.
    * @see #setId(String)
    * @see trafochainselection.TrafochainselectionPackage#getMetamodel_Id()
    * @model required="true"
    * @generated
    */
	String getId();

	/**
    * Sets the value of the '{@link trafochainselection.Metamodel#getId <em>Id</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Id</em>' attribute.
    * @see #getId()
    * @generated
    */
	void setId(String value);

} // Metamodel
