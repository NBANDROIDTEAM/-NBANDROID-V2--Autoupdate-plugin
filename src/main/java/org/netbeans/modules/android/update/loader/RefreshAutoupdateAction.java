/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.modules.android.update.loader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import org.netbeans.api.autoupdate.UpdateUnitProvider;
import org.netbeans.api.autoupdate.UpdateUnitProviderFactory;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.modules.ModuleInfo;
import org.openide.modules.Modules;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Tools",
        id = "org.netbeans.modules.android.update.loader.RefreshAutoupdateAction"
)

@ActionReference(path = "Menu/Tools" /*, position = 333 */)
@ActionRegistration(
        displayName = "#CTL_RefreshAutoupdateAction"
)
@Messages("CTL_RefreshAutoupdateAction=Refresh NBANDROID Update Site")
public final class RefreshAutoupdateAction implements ActionListener {

    private String implementationVersion;

    @Override
    public void actionPerformed(ActionEvent e) {
        UpdateUnitProviderFactory updateUnitProviderFactory = UpdateUnitProviderFactory.getDefault();
        ModuleInfo moduleInfo = Modules.getDefault().findCodeNameBase("org.netbeans.modules.projectui");
        implementationVersion = moduleInfo.getImplementationVersion();
        if (implementationVersion != null) {
            try {
                UpdateUnitProvider updateUnitProvider = updateUnitProviderFactory.create("NBANDROID", "NBANDROID Update Center", new URL("http://localhost:8080/updates/" + implementationVersion + "-updates.xml"));
                updateUnitProvider.setEnable(true);
            } catch (MalformedURLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
}
