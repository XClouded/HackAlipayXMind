HackAlipayXMind
===============
<p align="center" >
  <img src="./logo.jpg" alt="HackAlipayXMind" title="HackAlipayXMind">
</p>





#学习支付宝和淘宝客户端



##  学习
- 学习代码结构设计
- 采用UI总线，统一终端和前端
- 独有的插件体系
- Bundle 化架构

---

## 架构

### Bundle 化

- `BundleLoader`
- `QQApplicationContextImp`
- `ApplicationManagerImp`

### UIBus _( 隐式Intent )_

- `SchemeJumpActivity`
- `  <data android:scheme="app" android:host="www.qq.com" android:port="80" android:path="/demo/2"></data>
`

### Plugin System

- `PluginContext`
- `ClassLoader`


---

### Fragment.apk


```java public class HelloFragment extends Fragment{
	
	 private Context context;
	 private LayoutInflater inflater;

	  public View onCreateView(LayoutInflater paramLayoutInflater, 			ViewGroup paramViewGroup, Bundle paramBundle)
	  {
	    return 		LayoutInflater.from(QQApplication.getOutContext()).inflate(R.layout.fragment1, null);
	  }

	  @SuppressLint({"NewApi"})
	  public void onViewCreated(View paramView, Bundle paramBundle)
	  {
	    super.onViewCreated(paramView, paramBundle);
	    paramView.findViewById(R.id.button1).setOnClickListener(new 		View.OnClickListener()
	    {
	      public void onClick(View paramView)
	      {
	        Log.e("I am in HelloFragment", "I am in Here");
	      }
	    });
	  }
}
```


---

### Version 
 
V 1.0 alpha demo  

代码简陋，仅仅表达思想


