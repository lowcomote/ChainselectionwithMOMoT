/**
 */
package trafochainselection.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import trafochainselection.Metamodel;
import trafochainselection.TrafochainselectionPackage;
import trafochainselection.Transformation;
import trafochainselection.TransformationChain;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transformation Chain</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link trafochainselection.impl.TransformationChainImpl#getId <em>Id</em>}</li>
 *   <li>{@link trafochainselection.impl.TransformationChainImpl#getInputModel <em>Input Model</em>}</li>
 *   <li>{@link trafochainselection.impl.TransformationChainImpl#getStart <em>Start</em>}</li>
 *   <li>{@link trafochainselection.impl.TransformationChainImpl#getFinal <em>Final</em>}</li>
 *   <li>{@link trafochainselection.impl.TransformationChainImpl#getUses <em>Uses</em>}</li>
 *   <li>{@link trafochainselection.impl.TransformationChainImpl#getOutputMM <em>Output MM</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TransformationChainImpl extends MinimalEObjectImpl.Container implements TransformationChain {
	/**
    * The default value of the '{@link #getId() <em>Id</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getId()
    * @generated
    * @ordered
    */
   protected static final String ID_EDEFAULT = null;
   /**
    * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getId()
    * @generated
    * @ordered
    */
   protected String id = ID_EDEFAULT;
   /**
    * The default value of the '{@link #getInputModel() <em>Input Model</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getInputModel()
    * @generated
    * @ordered
    */
   protected static final String INPUT_MODEL_EDEFAULT = null;
   /**
    * The cached value of the '{@link #getInputModel() <em>Input Model</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getInputModel()
    * @generated
    * @ordered
    */
   protected String inputModel = INPUT_MODEL_EDEFAULT;
   /**
    * The cached value of the '{@link #getStart() <em>Start</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getStart()
    * @generated
    * @ordered
    */
   protected Metamodel start;
   /**
    * The cached value of the '{@link #getFinal() <em>Final</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getFinal()
    * @generated
    * @ordered
    */
   protected Metamodel final_;
   /**
    * The cached value of the '{@link #getUses() <em>Uses</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getUses()
    * @generated
    * @ordered
    */
   protected EList<Transformation> uses;
   /**
    * The cached value of the '{@link #getOutputMM() <em>Output MM</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getOutputMM()
    * @generated
    * @ordered
    */
   protected Metamodel outputMM;

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected TransformationChainImpl() {
      super();
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
	protected EClass eStaticClass() {
      return TrafochainselectionPackage.Literals.TRANSFORMATION_CHAIN;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
	public String getId() {
      return id;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
	public void setId(String newId) {
      String oldId = id;
      id = newId;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TrafochainselectionPackage.TRANSFORMATION_CHAIN__ID, oldId, id));
   }

	/**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String getInputModel() {
      return inputModel;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void setInputModel(String newInputModel) {
      String oldInputModel = inputModel;
      inputModel = newInputModel;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TrafochainselectionPackage.TRANSFORMATION_CHAIN__INPUT_MODEL, oldInputModel, inputModel));
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
	public Metamodel getStart() {
      if (start != null && start.eIsProxy()) {
         InternalEObject oldStart = (InternalEObject)start;
         start = (Metamodel)eResolveProxy(oldStart);
         if (start != oldStart) {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, TrafochainselectionPackage.TRANSFORMATION_CHAIN__START, oldStart, start));
         }
      }
      return start;
   }

	/**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public Metamodel basicGetStart() {
      return start;
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
	public void setStart(Metamodel newStart) {
      Metamodel oldStart = start;
      start = newStart;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TrafochainselectionPackage.TRANSFORMATION_CHAIN__START, oldStart, start));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
	public Metamodel getFinal() {
      if (final_ != null && final_.eIsProxy()) {
         InternalEObject oldFinal = (InternalEObject)final_;
         final_ = (Metamodel)eResolveProxy(oldFinal);
         if (final_ != oldFinal) {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, TrafochainselectionPackage.TRANSFORMATION_CHAIN__FINAL, oldFinal, final_));
         }
      }
      return final_;
   }

	/**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public Metamodel basicGetFinal() {
      return final_;
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
	public void setFinal(Metamodel newFinal) {
      Metamodel oldFinal = final_;
      final_ = newFinal;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TrafochainselectionPackage.TRANSFORMATION_CHAIN__FINAL, oldFinal, final_));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Transformation> getUses() {
      if (uses == null) {
         uses = new EObjectResolvingEList<Transformation>(Transformation.class, this, TrafochainselectionPackage.TRANSFORMATION_CHAIN__USES);
      }
      return uses;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
	public Metamodel getOutputMM() {
      if (outputMM != null && outputMM.eIsProxy()) {
         InternalEObject oldOutputMM = (InternalEObject)outputMM;
         outputMM = (Metamodel)eResolveProxy(oldOutputMM);
         if (outputMM != oldOutputMM) {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, TrafochainselectionPackage.TRANSFORMATION_CHAIN__OUTPUT_MM, oldOutputMM, outputMM));
         }
      }
      return outputMM;
   }

	/**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public Metamodel basicGetOutputMM() {
      return outputMM;
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
	public void setOutputMM(Metamodel newOutputMM) {
      Metamodel oldOutputMM = outputMM;
      outputMM = newOutputMM;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TrafochainselectionPackage.TRANSFORMATION_CHAIN__OUTPUT_MM, oldOutputMM, outputMM));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType) {
      switch (featureID) {
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN__ID:
            return getId();
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN__INPUT_MODEL:
            return getInputModel();
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN__START:
            if (resolve) return getStart();
            return basicGetStart();
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN__FINAL:
            if (resolve) return getFinal();
            return basicGetFinal();
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN__USES:
            return getUses();
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN__OUTPUT_MM:
            if (resolve) return getOutputMM();
            return basicGetOutputMM();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @SuppressWarnings("unchecked")
   @Override
   public void eSet(int featureID, Object newValue) {
      switch (featureID) {
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN__ID:
            setId((String)newValue);
            return;
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN__INPUT_MODEL:
            setInputModel((String)newValue);
            return;
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN__START:
            setStart((Metamodel)newValue);
            return;
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN__FINAL:
            setFinal((Metamodel)newValue);
            return;
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN__USES:
            getUses().clear();
            getUses().addAll((Collection<? extends Transformation>)newValue);
            return;
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN__OUTPUT_MM:
            setOutputMM((Metamodel)newValue);
            return;
      }
      super.eSet(featureID, newValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void eUnset(int featureID) {
      switch (featureID) {
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN__ID:
            setId(ID_EDEFAULT);
            return;
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN__INPUT_MODEL:
            setInputModel(INPUT_MODEL_EDEFAULT);
            return;
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN__START:
            setStart((Metamodel)null);
            return;
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN__FINAL:
            setFinal((Metamodel)null);
            return;
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN__USES:
            getUses().clear();
            return;
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN__OUTPUT_MM:
            setOutputMM((Metamodel)null);
            return;
      }
      super.eUnset(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public boolean eIsSet(int featureID) {
      switch (featureID) {
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN__ID:
            return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN__INPUT_MODEL:
            return INPUT_MODEL_EDEFAULT == null ? inputModel != null : !INPUT_MODEL_EDEFAULT.equals(inputModel);
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN__START:
            return start != null;
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN__FINAL:
            return final_ != null;
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN__USES:
            return uses != null && !uses.isEmpty();
         case TrafochainselectionPackage.TRANSFORMATION_CHAIN__OUTPUT_MM:
            return outputMM != null;
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String toString() {
      if (eIsProxy()) return super.toString();

      StringBuilder result = new StringBuilder(super.toString());
      result.append(" (id: ");
      result.append(id);
      result.append(", inputModel: ");
      result.append(inputModel);
      result.append(')');
      return result.toString();
   }

} //TransformationChainImpl
