/**
 */
package trafochainselection.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import trafochainselection.Metamodel;
import trafochainselection.ModelTransformationRepository;
import trafochainselection.TrafochainselectionPackage;
import trafochainselection.Transformation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Transformation Repository</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link trafochainselection.impl.ModelTransformationRepositoryImpl#getTransformations <em>Transformations</em>}</li>
 *   <li>{@link trafochainselection.impl.ModelTransformationRepositoryImpl#getMetamodels <em>Metamodels</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ModelTransformationRepositoryImpl extends MinimalEObjectImpl.Container implements ModelTransformationRepository {
	/**
    * The cached value of the '{@link #getTransformations() <em>Transformations</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTransformations()
    * @generated
    * @ordered
    */
   protected EList<Transformation> transformations;
   /**
    * The cached value of the '{@link #getMetamodels() <em>Metamodels</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getMetamodels()
    * @generated
    * @ordered
    */
   protected EList<Metamodel> metamodels;

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected ModelTransformationRepositoryImpl() {
      super();
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
	protected EClass eStaticClass() {
      return TrafochainselectionPackage.Literals.MODEL_TRANSFORMATION_REPOSITORY;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Transformation> getTransformations() {
      if (transformations == null) {
         transformations = new EObjectContainmentEList<Transformation>(Transformation.class, this, TrafochainselectionPackage.MODEL_TRANSFORMATION_REPOSITORY__TRANSFORMATIONS);
      }
      return transformations;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Metamodel> getMetamodels() {
      if (metamodels == null) {
         metamodels = new EObjectContainmentEList<Metamodel>(Metamodel.class, this, TrafochainselectionPackage.MODEL_TRANSFORMATION_REPOSITORY__METAMODELS);
      }
      return metamodels;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
      switch (featureID) {
         case TrafochainselectionPackage.MODEL_TRANSFORMATION_REPOSITORY__TRANSFORMATIONS:
            return ((InternalEList<?>)getTransformations()).basicRemove(otherEnd, msgs);
         case TrafochainselectionPackage.MODEL_TRANSFORMATION_REPOSITORY__METAMODELS:
            return ((InternalEList<?>)getMetamodels()).basicRemove(otherEnd, msgs);
      }
      return super.eInverseRemove(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType) {
      switch (featureID) {
         case TrafochainselectionPackage.MODEL_TRANSFORMATION_REPOSITORY__TRANSFORMATIONS:
            return getTransformations();
         case TrafochainselectionPackage.MODEL_TRANSFORMATION_REPOSITORY__METAMODELS:
            return getMetamodels();
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
         case TrafochainselectionPackage.MODEL_TRANSFORMATION_REPOSITORY__TRANSFORMATIONS:
            getTransformations().clear();
            getTransformations().addAll((Collection<? extends Transformation>)newValue);
            return;
         case TrafochainselectionPackage.MODEL_TRANSFORMATION_REPOSITORY__METAMODELS:
            getMetamodels().clear();
            getMetamodels().addAll((Collection<? extends Metamodel>)newValue);
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
         case TrafochainselectionPackage.MODEL_TRANSFORMATION_REPOSITORY__TRANSFORMATIONS:
            getTransformations().clear();
            return;
         case TrafochainselectionPackage.MODEL_TRANSFORMATION_REPOSITORY__METAMODELS:
            getMetamodels().clear();
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
         case TrafochainselectionPackage.MODEL_TRANSFORMATION_REPOSITORY__TRANSFORMATIONS:
            return transformations != null && !transformations.isEmpty();
         case TrafochainselectionPackage.MODEL_TRANSFORMATION_REPOSITORY__METAMODELS:
            return metamodels != null && !metamodels.isEmpty();
      }
      return super.eIsSet(featureID);
   }

} //ModelTransformationRepositoryImpl
