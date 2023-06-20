/**
 */
package trafochainselection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transformation Chain</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link trafochainselection.TransformationChain#getId <em>Id</em>}</li>
 *   <li>{@link trafochainselection.TransformationChain#getInputModel <em>Input Model</em>}</li>
 *   <li>{@link trafochainselection.TransformationChain#getStart <em>Start</em>}</li>
 *   <li>{@link trafochainselection.TransformationChain#getFinal <em>Final</em>}</li>
 *   <li>{@link trafochainselection.TransformationChain#getUses <em>Uses</em>}</li>
 *   <li>{@link trafochainselection.TransformationChain#getOutputMM <em>Output MM</em>}</li>
 * </ul>
 *
 * @see trafochainselection.TrafochainselectionPackage#getTransformationChain()
 * @model
 * @generated
 */
public interface TransformationChain extends EObject {
	/**
    * Returns the value of the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Id</em>' attribute.
    * @see #setId(String)
    * @see trafochainselection.TrafochainselectionPackage#getTransformationChain_Id()
    * @model required="true"
    * @generated
    */
	String getId();

	/**
    * Sets the value of the '{@link trafochainselection.TransformationChain#getId <em>Id</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Id</em>' attribute.
    * @see #getId()
    * @generated
    */
	void setId(String value);

	/**
    * Returns the value of the '<em><b>Input Model</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the value of the '<em>Input Model</em>' attribute.
    * @see #setInputModel(String)
    * @see trafochainselection.TrafochainselectionPackage#getTransformationChain_InputModel()
    * @model required="true"
    * @generated
    */
   String getInputModel();

   /**
    * Sets the value of the '{@link trafochainselection.TransformationChain#getInputModel <em>Input Model</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Input Model</em>' attribute.
    * @see #getInputModel()
    * @generated
    */
   void setInputModel(String value);

   /**
    * Returns the value of the '<em><b>Start</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Start</em>' reference.
    * @see #setStart(Metamodel)
    * @see trafochainselection.TrafochainselectionPackage#getTransformationChain_Start()
    * @model required="true"
    * @generated
    */
	Metamodel getStart();

	/**
    * Sets the value of the '{@link trafochainselection.TransformationChain#getStart <em>Start</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Start</em>' reference.
    * @see #getStart()
    * @generated
    */
	void setStart(Metamodel value);

	/**
    * Returns the value of the '<em><b>Final</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Final</em>' reference.
    * @see #setFinal(Metamodel)
    * @see trafochainselection.TrafochainselectionPackage#getTransformationChain_Final()
    * @model required="true"
    * @generated
    */
	Metamodel getFinal();

	/**
    * Sets the value of the '{@link trafochainselection.TransformationChain#getFinal <em>Final</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Final</em>' reference.
    * @see #getFinal()
    * @generated
    */
	void setFinal(Metamodel value);

	/**
    * Returns the value of the '<em><b>Uses</b></em>' reference list.
    * The list contents are of type {@link trafochainselection.Transformation}.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Uses</em>' reference list.
    * @see trafochainselection.TrafochainselectionPackage#getTransformationChain_Uses()
    * @model
    * @generated
    */
	EList<Transformation> getUses();

	/**
    * Returns the value of the '<em><b>Output MM</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Output MM</em>' reference.
    * @see #setOutputMM(Metamodel)
    * @see trafochainselection.TrafochainselectionPackage#getTransformationChain_OutputMM()
    * @model
    * @generated
    */
	Metamodel getOutputMM();

	/**
    * Sets the value of the '{@link trafochainselection.TransformationChain#getOutputMM <em>Output MM</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Output MM</em>' reference.
    * @see #getOutputMM()
    * @generated
    */
	void setOutputMM(Metamodel value);

} // TransformationChain
