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

<pre>allprojects {
    repositories { 
			...
			maven { url 'https://jitpack.io' }
		}
	}</pre>
  
在app目录下的build.gradle下添加：

<pre>implementation 'com.github.Jerry930326:UnderLineRadioBtn:latest.release'</pre>


<h4> Step 2：xml中使用： </h4>

<pre>
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
            app:lineW="0.5"
            tools:ignore="HardcodedText"/>
            </pre>
