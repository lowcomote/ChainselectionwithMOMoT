/**
 */
package trafochainselection.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import trafochainselection.Metamodel;
import trafochainselection.TrafochainselectionPackage;
import trafochainselection.Transformation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transformation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link trafochainselection.impl.TransformationImpl#getId <em>Id</em>}</li>
 *   <li>{@link trafochainselection.impl.TransformationImpl#getSrc <em>Src</em>}</li>
 *   <li>{@link trafochainselection.impl.TransformationImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link trafochainselection.impl.TransformationImpl#getComplexity <em>Complexity</em>}</li>
 *   <li>{@link trafochainselection.impl.TransformationImpl#getCoverage <em>Coverage</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TransformationImpl extends MinimalEObjectImpl.Container implements Transformation {
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
    * The cached value of the '{@link #getSrc() <em>Src</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSrc()
    * @generated
    * @ordered
    */
   protected Metamodel src;

   /**
    * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTarget()
    * @generated
    * @ordered
    */
   protected Metamodel target;

   /**
    * The default value of the '{@link #getComplexity() <em>Complexity</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getComplexity()
    * @generated
    * @ordered
    */
   protected static final int COMPLEXITY_EDEFAULT = 0;

   /**
    * The cached value of the '{@link #getComplexity() <em>Complexity</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getComplexity()
    * @generated
    * @ordered
    */
   protected int complexity = COMPLEXITY_EDEFAULT;

   /**
    * The default value of the '{@link #getCoverage() <em>Coverage</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getCoverage()
    * @generated
    * @ordered
    */
   protected static final double COVERAGE_EDEFAULT = 0.0;

   /**
    * The cached value of the '{@link #getCoverage() <em>Coverage</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getCoverage()
    * @generated
    * @ordered
    */
   protected double coverage = COVERAGE_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected TransformationImpl() {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected EClass eStaticClass() {
      return TrafochainselectionPackage.Literals.TRANSFORMATION;
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
         eNotify(new ENotificationImpl(this, Notification.SET, TrafochainselectionPackage.TRANSFORMATION__ID, oldId, id));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Metamodel getSrc() {
      if (src != null && src.eIsProxy()) {
         InternalEObject oldSrc = (InternalEObject)src;
         src = (Metamodel)eResolveProxy(oldSrc);
         if (src != oldSrc) {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, TrafochainselectionPackage.TRANSFORMATION__SRC, oldSrc, src));
         }
      }
      return src;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public Metamodel basicGetSrc() {
      return src;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void setSrc(Metamodel newSrc) {
      Metamodel oldSrc = src;
      src = newSrc;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TrafochainselectionPackage.TRANSFORMATION__SRC, oldSrc, src));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Metamodel getTarget() {
      if (target != null && target.eIsProxy()) {
         InternalEObject oldTarget = (InternalEObject)target;
         target = (Metamodel)eResolveProxy(oldTarget);
         if (target != oldTarget) {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, TrafochainselectionPackage.TRANSFORMATION__TARGET, oldTarget, target));
         }
      }
      return target;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public Metamodel basicGetTarget() {
      return target;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void setTarget(Metamodel newTarget) {
      Metamodel oldTarget = target;
      target = newTarget;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TrafochainselectionPackage.TRANSFORMATION__TARGET, oldTarget, target));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public int getComplexity() {
      return complexity;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void setComplexity(int newComplexity) {
      int oldComplexity = complexity;
      complexity = newComplexity;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TrafochainselectionPackage.TRANSFORMATION__COMPLEXITY, oldComplexity, complexity));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public double getCoverage() {
      return coverage;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void setCoverage(double newCoverage) {
      double oldCoverage = coverage;
      coverage = newCoverage;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TrafochainselectionPackage.TRANSFORMATION__COVERAGE, oldCoverage, coverage));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType) {
      switch (featureID) {
         case TrafochainselectionPackage.TRANSFORMATION__ID:
            return getId();
         case TrafochainselectionPackage.TRANSFORMATION__SRC:
            if (resolve) return getSrc();
            return basicGetSrc();
         case TrafochainselectionPackage.TRANSFORMATION__TARGET:
            if (resolve) return getTarget();
            return basicGetTarget();
         case TrafochainselectionPackage.TRANSFORMATION__COMPLEXITY:
            return getComplexity();
         case TrafochainselectionPackage.TRANSFORMATION__COVERAGE:
            return getCoverage();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void eSet(int featureID, Object newValue) {
      switch (featureID) {
         case TrafochainselectionPackage.TRANSFORMATION__ID:
            setId((String)newValue);
            return;
         case TrafochainselectionPackage.TRANSFORMATION__SRC:
            setSrc((Metamodel)newValue);
            return;
         case TrafochainselectionPackage.TRANSFORMATION__TARGET:
            setTarget((Metamodel)newValue);
            return;
         case TrafochainselectionPackage.TRANSFORMATION__COMPLEXITY:
            setComplexity((Integer)newValue);
            return;
         case TrafochainselectionPackage.TRANSFORMATION__COVERAGE:
            setCoverage((Double)newValue);
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
         case TrafochainselectionPackage.TRANSFORMATION__ID:
            setId(ID_EDEFAULT);
            return;
         case TrafochainselectionPackage.TRANSFORMATION__SRC:
            setSrc((Metamodel)null);
            return;
         case TrafochainselectionPackage.TRANSFORMATION__TARGET:
            setTarget((Metamodel)null);
            return;
         case TrafochainselectionPackage.TRANSFORMATION__COMPLEXITY:
            setComplexity(COMPLEXITY_EDEFAULT);
            return;
         case TrafochainselectionPackage.TRANSFORMATION__COVERAGE:
            setCoverage(COVERAGE_EDEFAULT);
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
         case TrafochainselectionPackage.TRANSFORMATION__ID:
            return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
         case TrafochainselectionPackage.TRANSFORMATION__SRC:
            return src != null;
         case TrafochainselectionPackage.TRANSFORMATION__TARGET:
            return target != null;
         case TrafochainselectionPackage.TRANSFORMATION__COMPLEXITY:
            return complexity != COMPLEXITY_EDEFAULT;
         case TrafochainselectionPackage.TRANSFORMATION__COVERAGE:
            return coverage != COVERAGE_EDEFAULT;
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
      result.append(", complexity: ");
      result.append(complexity);
      result.append(", coverage: ");
      result.append(coverage);
      result.append(')');
      return result.toString();
   }

} //TransformationImpl
