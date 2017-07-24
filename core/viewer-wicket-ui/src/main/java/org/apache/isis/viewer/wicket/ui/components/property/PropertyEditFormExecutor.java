package org.apache.isis.viewer.wicket.ui.components.property;

import java.util.concurrent.Callable;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.request.cycle.RequestCycle;

import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.core.metamodel.adapter.mgr.AdapterManager;
import org.apache.isis.viewer.wicket.model.models.ScalarModel;
import org.apache.isis.viewer.wicket.ui.pages.entity.EntityPage;
import org.apache.isis.viewer.wicket.ui.panels.FormExecutorDefault;

public class PropertyEditFormExecutor implements FormExecutorDefault.FormExecutorStrategy {

    private final ScalarModel model;

    public PropertyEditFormExecutor(final ScalarModel scalarModel) {
        model = scalarModel;
    }

    public ObjectAdapter obtainTargetAdapter() {
        return model.getParentEntityModel().load();
    }

    public String getReasonInvalidIfAny() {
        return this.model.getReasonInvalidIfAny();
    }


    public void onExecuteAndProcessResults(final AjaxRequestTarget target) {
        // no-op
    }

    public ObjectAdapter obtainResultAdapter() {
        ObjectAdapter targetAdapter = obtainTargetAdapter();

        final ObjectAdapter resultAdapter = this.model.applyValue(targetAdapter);

        if (resultAdapter != targetAdapter) {
            this.model.getParentEntityModel().setObject(targetAdapter);
        }
        return resultAdapter;
    }


    public void redirectTo(
            final ObjectAdapter resultAdapter,
            final AjaxRequestTarget target) {
        // disabling concurrency checking after the layout XML (grid) feature
        // was throwing an exception when rebuild grid after invoking edit prompt.
        // not certain why that would be the case, but (following similar code for action prompt)
        // think it should be safe to simply disable while recreating the page to re-render back to user.
        final EntityPage entityPage =
                AdapterManager.ConcurrencyChecking.executeWithConcurrencyCheckingDisabled(
                        new Callable<EntityPage>() {
                            @Override public EntityPage call() throws Exception {
                                return new EntityPage(resultAdapter, null);
                            }
                        }
                );

        final RequestCycle requestCycle = RequestCycle.get();
        requestCycle.setResponsePage(entityPage);
    }


}
