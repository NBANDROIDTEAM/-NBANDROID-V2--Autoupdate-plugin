/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.modules.android.update.loader;

import java.net.MalformedURLException;
import java.net.URL;
import org.netbeans.api.autoupdate.UpdateUnitProvider;
import org.netbeans.api.autoupdate.UpdateUnitProviderFactory;
import org.openide.modules.ModuleInfo;
import org.openide.modules.ModuleInstall;
import org.openide.modules.Modules;
import org.openide.util.Exceptions;

public class Installer extends ModuleInstall {

    private String implementationVersion;

    @Override
    public void restored() {
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
