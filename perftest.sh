#!/bin/bash
java -Xms32m -Xmx2048m -cp lib/derbyclient.jar:lib/jt400.jar:lib/ibankapi.jar:lib/ibankgui.jar demo.iBankPerfTest $*
