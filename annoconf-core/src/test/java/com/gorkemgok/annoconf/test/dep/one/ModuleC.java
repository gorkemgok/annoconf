package com.gorkemgok.annoconf.test.dep.one;

import com.gorkemgok.annoconf.annotation.LoadService;

/**
 * Created by gorkem on 21.07.2017.
 */
@LoadService(name = "module-c", implementationOf = "module-c")
public class ModuleC {
}
