# UnderLineRadioBtn
[![](https://jitpack.io/v/Jerry930326/UnderLineRadioBtn.svg)](https://jitpack.io/#Jerry930326/UnderLineRadioBtn)

这是一个带有下划线的自定义RadioButton

# 项目简介：
<h4> 自定义属性 </h4>
<table>
  <tr>
    <td>属性名</td>
    <td>取值</td>
    <td>释义</td>
  </tr>
  <tr>
    <td>lineH</td>
    <td>dimension</td>
    <td>线的高度(0则没有线)</td>
  </tr>
  <tr>
    <td>lineW</td>
    <td>float|dimension</td>
    <td>线的宽度(float为百分比:例如0.5代表RadioButton的1/2宽)</td>
  </tr>
  <tr>
    <td>lineCheckColor</td>
    <td>color</td>
    <td>选中时线的颜色(默认:#ff1819)</td>
  </tr>
  <tr>
    <td>lineDefaultColor</td>
    <td>color</td>
    <td>未选中时线的颜色(默认:#202020)</td>
  </tr>
  <tr>
    <td>textCheckColor</td>
    <td>color</td>
    <td>选中时文字的颜色(默认:#202020)</td>
  </tr>
  <tr>
    <td>textDefaultColor</td>
    <td>color</td>
    <td>未选中时文字的颜色(默认:#202020)</td>
  </tr>
  <tr>
    <td>lineRadius</td>
    <td>dimension</td>
    <td>线的圆角，0或不设置为矩形，否则为圆角矩形</td>
  </tr>
  </table>

# 使用方式：
<h4> Step 1：添加依赖 </h4>
在Project目录的build.gradle下添加:

```
allprojects {
    repositories { 
	...
	maven { url 'https://jitpack.io' }
    }
}
```
  
在app目录下的build.gradle下添加：

```
implementation 'com.github.Jerry930326:UnderLineRadioBtn:latest.release'
```

<h4> Step 2：xml中使用： </h4>

```
<UnderLineRadioBtn
    android:id="@+id/id1"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:layout_weight="1"
    android:background="@null"
    android:button="@null"
    android:gravity="center"
    android:text="带下划线的RadioButton示例"
    android:textSize="20sp"
    app:lineH="6dp"
    app:lineRadius="5dp"
    app:lineW="0.5" />
```

## License

This library is licensed under the [Apache Software License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

See [`LICENSE`](LICENSE) for full of the license text.

    Copyright (C) 2018 Jerry930326

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
