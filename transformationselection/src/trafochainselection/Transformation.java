/**
 */
package trafochainselection;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transformation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link trafochainselection.Transformation#getId <em>Id</em>}</li>
 *   <li>{@link trafochainselection.Transformation#getSrc <em>Src</em>}</li>
 *   <li>{@link trafochainselection.Transformation#getTarget <em>Target</em>}</li>
 *   <li>{@link trafochainselection.Transformation#getComplexity <em>Complexity</em>}</li>
 *   <li>{@link trafochainselection.Transformation#getCoverage <em>Coverage</em>}</li>
 * </ul>
 *
 * @see trafochainselection.TrafochainselectionPackage#getTransformation()
 * @model
 * @generated
 */
public interface Transformation extends EObject {
   /**
    * Returns the value of the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the value of the '<em>Id</em>' attribute.
    * @see #setId(String)
    * @see trafochainselection.TrafochainselectionPackage#getTransformation_Id()
    * @model required="true"
    * @generated
    */
   String getId();

   /**
    * Sets the value of the '{@link trafochainselection.Transformation#getId <em>Id</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Id</em>' attribute.
    * @see #getId()
    * @generated
    */
   void setId(String value);

   /**
    * Returns the value of the '<em><b>Src</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the value of the '<em>Src</em>' reference.
    * @see #setSrc(Metamodel)
    * @see trafochainselection.TrafochainselectionPackage#getTransformation_Src()
    * @model required="true"
    * @generated
    */
   Metamodel getSrc();

   /**
    * Sets the value of the '{@link trafochainselection.Transformation#getSrc <em>Src</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Src</em>' reference.
    * @see #getSrc()
    * @generated
    */
   void setSrc(Metamodel value);

   /**
    * Returns the value of the '<em><b>Target</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the value of the '<em>Target</em>' reference.
    * @see #setTarget(Metamodel)
    * @see trafochainselection.TrafochainselectionPackage#getTransformation_Target()
    * @model required="true"
    * @generated
    */
   Metamodel getTarget();

   /**
    * Sets the value of the '{@link trafochainselection.Transformation#getTarget <em>Target</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Target</em>' reference.
    * @see #getTarget()
    * @generated
    */
   void setTarget(Metamodel value);

   /**
    * Returns the value of the '<em><b>Complexity</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the value of the '<em>Complexity</em>' attribute.
    * @see #setComplexity(int)
    * @see trafochainselection.TrafochainselectionPackage#getTransformation_Complexity()
    * @model
    * @generated
    */
   int getComplexity();

   /**
    * Sets the value of the '{@link trafochainselection.Transformation#getComplexity <em>Complexity</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Complexity</em>' attribute.
    * @see #getComplexity()
    * @generated
    */
   void setComplexity(int value);

   /**
    * Returns the value of the '<em><b>Coverage</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the value of the '<em>Coverage</em>' attribute.
    * @see #setCoverage(double)
    * @see trafochainselection.TrafochainselectionPackage#getTransformation_Coverage()
    * @model
    * @generated
    */
   double getCoverage();

   /**
    * Sets the value of the '{@link trafochainselection.Transformation#getCoverage <em>Coverage</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Coverage</em>' attribute.
    * @see #getCoverage()
    * @generated
    */
   void setCoverage(double value);

} // Transformation
