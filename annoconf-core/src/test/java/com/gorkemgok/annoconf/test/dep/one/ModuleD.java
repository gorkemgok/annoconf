package com.gorkemgok.annoconf.test.dep.one;

import com.gorkemgok.annoconf.annotation.LoadService;

/**
 * Created by gorkem on 21.07.2017.
 */
@LoadService(name = "module-d1", autoLoad = true, implementationOf = "module-d", dependingModules = "module-a")
public class ModuleD {
}
