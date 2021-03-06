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
package jp.llv.ce.region.owned;

import jp.llv.ce.region.Region;

/**
 *
 * @author Toyblocks
 * @param <R> The region to be owned
 */
public final class OwnedAbsoluteRegion<R extends Region> extends OwnedRegion<R> {
    
    private final R region;

    public OwnedAbsoluteRegion(R region, RegionOwner player, int id) {
        super(player, id);
        if (region == null) {
            throw new NullPointerException();
        }
        this.region = region;
    }

    @Override
    public Region toRegion() {
        return this.region;
    }

    @Override
    public R getSource() {
        return this.region;
    }
    
}
