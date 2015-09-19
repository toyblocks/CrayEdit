/* 
 * The MIT License
 *
 * Copyright 2015 Toyblocks.
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
package jp.llv.ce.regions.relative;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 相対領域を複数格納し、一つの領域として扱う. Compositeパターン。 領域A,B,Cが与えられたとき、A-B-C...の領域として扱われる。
 * 初期化以降の領域単位での編集は不可
 *
 * @author Toyblocks
 */
public class RelativeNOTContainer extends RelativeRegion {

    private final RelativeRegion union;
    private final Set<? extends RelativeRegion> contents;

    public RelativeNOTContainer(RelativeRegion union, Collection<? extends RelativeRegion> contents) {
        this.union = union;
        this.contents = new LinkedHashSet<>(contents);
    }

    public RelativeNOTContainer(RelativeRegion union, RelativeRegion... contents) {
        this(union, Arrays.asList(contents));
    }

    public RelativeRegion getUnion() {
        return this.union;
    }
    
    public Set<RelativeRegion> getRegions() {
        return Collections.unmodifiableSet(contents);
    }

    @Override
    public Set<RelativePoint> toPoints() {
        Set<RelativePoint> result = new LinkedHashSet<>(union.toPoints());
        for (RelativeRegion r : contents) {
            result.removeAll(r.toPoints());
        }
        return Collections.unmodifiableSet(result);
    }

}