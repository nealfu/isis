package org.nakedobjects.object;

import org.nakedobjects.object.collection.InternalCollection;
import org.nakedobjects.object.collection.SimpleInternalCollection;
import org.nakedobjects.object.control.FieldAbout;
import org.nakedobjects.security.Role;
import org.nakedobjects.security.User;

import org.apache.log4j.Logger;



public abstract class AbstractUserContext extends AbstractNakedObject implements UserContext {
    private static final Logger LOG = Logger.getLogger(AbstractUserContext.class);
    
    public static String fieldOrder() {
    	return "user, classes, objects";
    }
    	
	private final InternalCollection classes = new SimpleInternalCollection(NakedClass.class, this);
    private final InternalCollection objects = new SimpleInternalCollection(NakedObject.class, this);
	private User user;
    
    public void aboutClasses(FieldAbout about, NakedObject element, boolean add) {
    	about.modifiableOnlyByRole(Role.SYSADMIN);
    }
    
    public void aboutObjects(FieldAbout about, NakedObject element, boolean add) {
    	about.modifiableOnlyByUser(user);
    }
    
    public void aboutUser(FieldAbout about, User user) {
    	about.modifiableOnlyByRole(Role.SYSADMIN);
    }
    
    protected NakedClass addClass(Class class1) {
        LOG.info("Added class " + class1 + " to " + this);
		NakedObjectSpecification nc = NakedObjectSpecification.getNakedClass(class1.getName());
		NakedClass nakedClass = getObjectManager().getNakedClass(nc);
        classes.add(nakedClass);
        return nakedClass;
	}
	
	protected NakedClass addClass(String className) {
	     LOG.info("Added class " + className + " to " + this);
	    NakedObjectSpecification nc = NakedObjectSpecification.getNakedClass(className);
	    NakedClass nakedClass = getObjectManager().getNakedClass(nc);
	    getObjectManager().makePersistent(nakedClass);
        classes.add(nakedClass);
        return nakedClass;
	}

	public void associateUser(User user) {
		user.setRootObject(this);
		this.setUser(user);
	}
	
	public void dissociateUser(User user) {
		user.setRootObject(null);
		this.setUser(null);
	}

    public InternalCollection getClasses() {
        return classes;
    }
    
    public InternalCollection getObjects() {
    	return objects;
    }
    
    public User getUser() {
    	resolve(user);
    	return user;
    }
    	
    public void setUser(User user) {
    	this.user = user;
    	objectChanged();
    }
}
