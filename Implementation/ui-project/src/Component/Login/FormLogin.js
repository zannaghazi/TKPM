import React from 'react';

function FormLogin(props) {
    return (
        <form>
            <input
                type="text"
                id="login"
                class="fadeIn second"
                name="login"
                placeholder="Tài khoản"
                v-model="userName"
            />
            <input
                type="password"
                id="password"
                class="fadeIn third"
                name="password"
                placeholder="Mật khẩu"
                v-model="passWord"
            />
            {/* <div v-if="err != ''" class="my-err-message">{{ err }}</div> */}
            <input type="submit" class="fadeIn fourth" defaultValue="Log In" value="Đăng nhập" />
        </form>
    );
}

export default FormLogin;