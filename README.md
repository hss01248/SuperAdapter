# SuperAdapter
listview和recycleview的adapter的封装,考虑重用性和可读性,不过度抽象

[![](https://jitpack.io/v/hss01248/SuperAdapter.svg)](https://jitpack.io/#hss01248/SuperAdapter)

[封装的说明] (http://www.jianshu.com/p/994de390378f)

# 使用

# gradle:

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.hss01248:SuperAdapter:2.0.2'
	}

# 使用

## 使用技巧: 

> listview,recycleview的数据与界面达到完全的一一对应,如果服务器返回的数据不对应,那么重新组合,如果有一个item无需数据,那么在datas里插入null或无意义的数据,holder多一种类型来处理即可.

数据引用由内部维护,无需外部传入.

## SuperLvAdapter: 

* 单一类型item时，使用匿名实现类即可，

* 多种类型时，依据数据集datas内部javabean的类型来做判断,无需重写getItemViewTypeCount和getItemViewType(position) ,只要在generateNewHolder里进行类型判断即可,示例代码如下;

  ```
  SuperLvAdapter adapter = new SuperLvAdapter<ListActivity>(this) {
              @Override
              protected SuperLvHolder generateNewHolder(ListActivity context, int itemViewType) {
                  if(itemViewType == Bean1.class.hashCode()){
                      return new Holder1(context);
                  }else if(itemViewType == Bean2.class.hashCode()){
                      return new Holder2(context);
                  }
                  return null;
              }
          }
  ```

  ​

## SuperLvHolder:

写子类的时候在子类内部指定layout文件,一般情况下,实现assingDatasAndEvents(Activity context, String bean)就可以,如果要用到int position ,boolean isLast,就实现更多参数的同名方法,此时,上面那个简化的方法空实现即可.

如果该holder在多个地方使用,那么可以作为单独的类,达到复用的目的.

## SuperRvAdapter和SuperRvHolder

adapter一般情况下都使用匿名子类.多个item时分别指定类型和对应的hoder即可.
holder一般也使用匿名子类.如果在其他页面需要复用,那么可以写成单独的子类.其layout文件需要在构造函数前传入,已封装好方法.





## 使用findviewByMe插件生成findviewbyid代码,取消butterknife,以防内存泄漏以及为防止其内存泄漏的复杂操作.

# 示例代码

## AbstractListview 的 SuperLvAdapter:

### adapter:(这里示例代码是只有一种itemtype的情况)

      ListView listView = new ListView(this);
     SuperLvAdapter adapter = new SuperLvAdapter<Activity>(this) {
                @Override
                protected SuperLvHolder generateNewHolder(Activity context, int itemViewType) {
                    if(itemViewType == Bean1.class.hashCode()){
                        return new Holder1(context);
                    }else if(itemViewType == Bean2.class.hashCode()){
                        return new Holder2(context);
                    }
                    return null;
                }
            };
     	adapter.addAll(datas);
        listView.setAdapter(adapter);


        adapter.add("hhhh");

​        
### viewholder的实现:




    public class Holder1 extends SuperLvHolder<Bean1,Activity> {
        @BindView(R.id.tv_text)
        TextView tvText;
    
        public Holder1(Activity context) {
            super(context);
        }
    
        @Override
        protected int setLayoutRes() {
            return R.layout.holder_demo_list;
        }
    
        @Override
        public void assingDatasAndEvents(Activity context, Bean1 bean) {
            tvText.setText(bean.toString());
        }
    }


## RecycleView 的 SuperRcvAdapter:

### SuperRcvAdapter,多种类型下的使用




    mAdapter = new SuperRvAdapter<MainActivity>( MainActivity.this) {

                @Override
                protected SuperRvHolder generateCoustomViewHolder(int viewType) {
                    if(viewType == String.class.hashCode()){
                        return new SuperRvHolder<String,MainActivity>(inflate(R.layout.holder_demo_list_2)) {//匿名子类
                            @Override
                            public void assignDatasAndEvents(MainActivity context, String data) {
                                super.assignDatasAndEvents(context, data);
                                ((TextView)(rootView.findViewById(R.id.tv_text))).setText(data+" string type");
                            }
                        };
                    }
    
                    if(viewType == Bean1.class.hashCode()){
                        return new CustomHolder(inflate(R.layout.holder_demo_list));
                    }
    
                    if(viewType == Bean2.class.hashCode()){
                        return new CustomHolder2(inflate(R.layout.holder_demo_list));
                    }
                    return new CustomHolder(inflate(R.layout.holder_demo_list));
    
                }
            };




### holder的实现:




     class CustomHolder extends SuperRvHolder<Bean1,MainActivity> {

            @BindView(R.id.tv_text)
            TextView mTvText;
    
            public CustomHolder(View itemView) {
                super(itemView);
            }


            @Override
            public void assignDatasAndEvents(MainActivity context, Bean1 data) {
                mTvText.setText(data.toString()+"  bean1 type");
            }
        }




 # 额外福利:CommonViewHolder
 通用性viewholder,非典型的MVVM的实现:

 ```
public abstract class CommonViewHolder<T,A extends Activity> {

    public View rootView;
    public A activity;

    public CommonViewHolder(A context){
        int layoutRes = setLayoutRes();
        this.activity = context;
        if(layoutRes !=0){
            rootView = (ViewGroup) View.inflate(context,setLayoutRes(),null);
        }else {
            rootView = setRootView(context);
            if(rootView ==null){
                throw new RuntimeException("setRootView is null !");
            }
        }

        //ButterKnife.bind(this,rootView);
        findViews();
    }

    protected ViewGroup setRootView(Context context) {
        return null;
    }

    protected abstract int setLayoutRes();

    protected abstract void findViews();

    public  abstract void assingDatasAndEvents(A activity, @Nullable T bean);
}
 
 ```

