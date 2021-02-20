# ViewDispatchEvent
### 了解事件分发机制的demo

### Example:
* 触摸事件传递过来后，ViewGroupA一看自己里面还有一个员工可以利用，就是ViewGroupB，那不用白不用，就会把这个事件传递给ViewGroupB，告诉他，你给我把这个事件处理了！
ViewGroupB呢一看，我不怕，我里面也有一个员工就是MyView，它得给我干活，于是又会把这个事件传递给MyView，让它来处理。MyView一看，没办法啊，我手底下没有员工了，那
怎么办，我只能自己处理了（前提是它有处理这个事件的能力），所以就把这个触摸事件给处理了。
* 处理完成后呢？MyView就是给ViewGroupB报告，我已经把事情办好了，你来审核一下
，看看办理的咋样。ViewGroupB一审核，觉得不错，就再将结果呈现给ViewGroupA。ViewGroupA再审核，通过了才算通过。在这个过程中，也可能出现几种情况：
> （1）MyView说，完蛋了，这事我的能力办不好啊，于是就向VeiwGroupB报告，我没有处理，请你来处理，你是我上司，能力比我强。于是ViewGroupB就会来帮忙处理。当然了，
    如果ViewGroupB也没能力处理，那就只能反馈给VeiwGroupA，让它来消化这个事件。

> （2）也可能MyView处理非常完美，向ViewGroupB一报告，ViewGroupB一开心就说不用再交给ViewGroupA审核了，我担保通过，于是事件到此直接终止。

### 参考：
* [android中的事件传递和处理机制](https://www.cnblogs.com/fuly550871915/p/4983682.html)