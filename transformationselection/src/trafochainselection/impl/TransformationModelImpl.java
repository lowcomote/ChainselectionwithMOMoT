/**
 */
package trafochainselection.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import trafochainselection.ModelTransformationRepository;
import trafochainselection.TrafochainselectionPackage;
import trafochainselection.TransformationChain;
import trafochainselection.TransformationModel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transformation Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link trafochainselection.impl.TransformationModelImpl#getTransformationchain <em>Transformationchain</em>}</li>
 *   <li>{@link trafochainselection.impl.TransformationModelImpl#getModelTransformationRepository <em>Model Transformation Repository</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TransformationModelImpl extends MinimalEObjectImpl.Container implements TransformationModel {
	/**
    * The cached value of the '{@link #getTransformationchain() <em>Transformationchain</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTransformationchain()
    * @generated
    * @ordered
    */
   protected TransformationChain transformationchain;
   /**
    * The cached value of the '{@link #getModelTransformationRepository() <em>Model Transformation Repository</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getModelTransformationRepository()
    * @generated
    * @ordered
    */
   protected ModelTransformationRepository modelTransformationRepository;

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected TransformationModelImpl() {
      super();
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
	protected EClass eStaticClass() {
      return TrafochainselectionPackage.Literals.TRANSFORMATION_MODEL;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
	public TransformationChain getTransformationchain() {
      return transformationchain;
   }

	/**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetTransformationchain(TransformationChain newTransformationchain, NotificationChain msgs) {
      TransformationChain oldTransformationchain = transformationchain;
      transformationchain = newTransformationchain;
      if (eNotificationRequired()) {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TrafochainselectionPackage.TRANSFORMATION_MODEL__TRANSFORMATIONCHAIN, oldTransformationchain, newTransformationchain);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
	public void setTransformationchain(TransformationChain newTransformationchain) {
      if (newTransformationchain != transformationchain) {
         NotificationChain msgs = null;
         if (transformationchain != null)
            msgs = ((InternalEObject)transformationchain).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TrafochainselectionPackage.TRANSFORMATION_MODEL__TRANSFORMATIONCHAIN, null, msgs);
         if (newTransformationchain != null)
            msgs = ((InternalEObject)newTransformationchain).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TrafochainselectionPackage.TRANSFORMATION_MODEL__TRANSFORMATIONCHAIN, null, msgs);
         msgs = basicSetTransformationchain(newTransformationchain, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TrafochainselectionPackage.TRANSFORMATION_MODEL__TRANSFORMATIONCHAIN, newTransformationchain, newTransformationchain));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
	public ModelTransformationRepository getModelTransformationRepository() {
      return modelTransformationRepository;
   }

	/**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetModelTransformationRepository(ModelTransformationRepository newModelTransformationRepository, NotificationChain msgs) {
      ModelTransformationRepository oldModelTransformationRepository = modelTransformationRepository;
      modelTransformationRepository = newModelTransformationRepository;
      if (eNotificationRequired()) {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TrafochainselectionPackage.TRANSFORMATION_MODEL__MODEL_TRANSFORMATION_REPOSITORY, oldModelTransformationRepository, newModelTransformationRepository);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
	public void setModelTransformationRepository(ModelTransformationRepository newModelTransformationRepository) {
      if (newModelTransformationRepository != modelTransformationRepository) {
         NotificationChain msgs = null;
         if (modelTransformationRepository != null)
            msgs = ((InternalEObject)modelTransformationRepository).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TrafochainselectionPackage.TRANSFORMATION_MODEL__MODEL_TRANSFORMATION_REPOSITORY, null, msgs);
         if (newModelTransformationRepository != null)
            msgs = ((InternalEObject)newModelTransformationRepository).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TrafochainselectionPackage.TRANSFORMATION_MODEL__MODEL_TRANSFORMATION_REPOSITORY, null, msgs);
         msgs = basicSetModelTransformationRepository(newModelTransformationRepository, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TrafochainselectionPackage.TRANSFORMATION_MODEL__MODEL_TRANSFORMATION_REPOSITORY, newModelTransformationRepository, newModelTransformationRepository));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
      switch (featureID) {
         case TrafochainselectionPackage.TRANSFORMATION_MODEL__TRANSFORMATIONCHAIN:
            return basicSetTransformationchain(null, msgs);
         case TrafochainselectionPackage.TRANSFORMATION_MODEL__MODEL_TRANSFORMATION_REPOSITORY:
            return basicSetModelTransformationRepository(null, msgs);
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
         case TrafochainselectionPackage.TRANSFORMATION_MODEL__TRANSFORMATIONCHAIN:
            return getTransformationchain();
         case TrafochainselectionPackage.TRANSFORMATION_MODEL__MODEL_TRANSFORMATION_REPOSITORY:
            return getModelTransformationRepository();
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
         case TrafochainselectionPackage.TRANSFORMATION_MODEL__TRANSFORMATIONCHAIN:
            setTransformationchain((TransformationChain)newValue);
            return;
         case TrafochainselectionPackage.TRANSFORMATION_MODEL__MODEL_TRANSFORMATION_REPOSITORY:
            setModelTransformationRepository((ModelTransformationRepository)newValue);
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
         case TrafochainselectionPackage.TRANSFORMATION_MODEL__TRANSFORMATIONCHAIN:
            setTransformationchain((TransformationChain)null);
            return;
         case TrafochainselectionPackage.TRANSFORMATION_MODEL__MODEL_TRANSFORMATION_REPOSITORY:
            setModelTransformationRepository((ModelTransformationRepository)null);
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
         case TrafochainselectionPackage.TRANSFORMATION_MODEL__TRANSFORMATIONCHAIN:
            return transformationchain != null;
         case TrafochainselectionPackage.TRANSFORMATION_MODEL__MODEL_TRANSFORMATION_REPOSITORY:
            return modelTransformationRepository != null;
      }
      return super.eIsSet(featureID);
   }

} //TransformationModelImpl
