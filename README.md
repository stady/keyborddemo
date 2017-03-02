# keyborddemo
仿微信支付宝数字键盘</br>
1.支持添加确定按钮点击事件</br>
2.支持 更改键盘样式 设置确定按钮是否可用</br>
3.支持确定按钮添加回调</br>

用法：
========


            <com.stady.keyboardnum.KeyboardEditText
                android:id="@+id/login_et_phone"
                style="@style/comm_validate_edt_style"
                android:layout_weight="1"
                android:background="@null"
                android:drawableLeft="@drawable/n_loginregister_login_phonet"
                android:hint="@string/please_input_username_phone"
                android:inputType="phone"
                android:maxLength="11"
                app:random_keys="false"
                app:xml="@xml/price_input_keyboard"
                android:tag="@string/login_username" />
