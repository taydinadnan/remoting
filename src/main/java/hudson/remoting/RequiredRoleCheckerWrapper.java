/*
 * The MIT License
 *
 * Copyright (c) 2021, CloudBees, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package hudson.remoting;

import edu.umd.cs.findbugs.annotations.NonNull;
import org.jenkinsci.remoting.Role;
import org.jenkinsci.remoting.RoleChecker;
import org.jenkinsci.remoting.RoleSensitive;

import java.util.Collection;

/**
 * Implementation that can tell whether a {@code #check} method was invoked.
 */
/* package-private */ class RequiredRoleCheckerWrapper extends RoleChecker {
    private boolean checked;

    private RoleChecker roleChecker;

    public RequiredRoleCheckerWrapper(RoleChecker roleChecker) {
        this.roleChecker = roleChecker;
    }

    @Override
    public void check(@NonNull RoleSensitive subject, Role... expected) throws SecurityException {
        checked = true;
        roleChecker.check(subject, expected);
    }

    @Override
    public void check(@NonNull RoleSensitive subject, @NonNull Role expected) throws SecurityException {
        checked = true;
        roleChecker.check(subject, expected);
    }

    @Override
    public void check(@NonNull RoleSensitive subject, @NonNull Collection<Role> expected) throws SecurityException {
        checked = true;
        roleChecker.check(subject, expected);
    }

    public boolean isChecked() {
        return checked;
    }
}
