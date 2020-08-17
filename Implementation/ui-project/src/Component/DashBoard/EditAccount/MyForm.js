import React, { useState, useEffect } from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import InputConTainer from './InputContainer'
import DatePickerContainer from './DatePickerContainer'
import RadioContainer from './RadioContainer'
import SelectedBoxContainer from './SelectedBoxContainer'
import ButtonContainer from './ButtonContainer';
import config from '../../../asset/config.json'
import { connect } from 'react-redux';
import { useHistory } from "react-router-dom";
import { useParams } from 'react-router-dom';

function Form(props) {
    const [account, setAccount] = useState([]);
    const [roles, setRoles] = useState([]);
    const [data, setData] = useState({});
    const history = useHistory();
    const params = useParams();

    useEffect(() => {
        let url = config.severAPi.hostUrl + ":8083/system/get_account_by_id/" + params.id;
        fetch(url, {
            method: "get",
            headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
        })
            .then(res => res.json())
            .then(dataReceive => {
                console.log("111111111111111", dataReceive)
                setData(dataReceive);
            });
    }, []);

    useEffect(() => {
        console.log("props in new account", props);
        setRoles(props.roles)
    }, [props.roles]);

    function changeFullName(dataTransfer) {
        let temp = data;
        console.log(temp);
        temp.fullName = dataTransfer;
        console.log(temp);
        setData(temp);
    }

    function changeAddress(dataTransfer) {
        let temp = data;
        temp.address = dataTransfer;
        console.log(temp);
        setData(temp);
    }

    function changeGender(dataTransfer) {
        let temp = data;
        if (dataTransfer === '1') {
            temp.gender = false
        }
        else {
            temp.gender = true
        }
        console.log(temp);
        setData(temp);
    }

    function changeUsername(dataTransfer) {
        let temp = data;
        temp.username = dataTransfer;
        console.log(temp);
        setData(temp);
    }

    function changePassword(dataTransfer) {
        let temp = data;
        temp.password = dataTransfer;
        console.log(temp);
        setData(temp);
    }

    function changeBirthDay(dataTransfer) {
        let temp = data;
        temp.birthDay = dataTransfer;
        console.log(temp);
        setData(temp);
    }

    function changeRole(dataTransfer) {
        let temp = data;
        temp.role = dataTransfer;
        console.log(temp);
        setData(temp);
    }

    function edit() {
        if (data.fullName == "" || data.address == "" || data.birthDay == "" || data.username == "" || data.role == -1) {
            alert("Dữ liệu nhập vào không hợp lệ");
            return;
        }

        let url = config.severAPi.hostUrl + ":8083/system/update_account";
        fetch(url, {
            method: "post",
            headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
            body: JSON.stringify(data)
        })
            .then(res => res.json())
            .then(data => {
                if (data.result) {
                    alert("Cập nhật tài khoản thành công!");
                }
                else {
                    alert("Đã có lỗi xảy ra. Vui lòng kiểm tra lại!")
                    return;
                }
            });
    }

    return (
        <Container>
            <InputConTainer label="Tên:" byData={changeFullName} value={data.fullName}/>
            <InputConTainer label="Tên tài khoản:" byData={changeUsername} value={data.username}/>
            <InputConTainer label="Địa chỉ:" byData={changeAddress} value={data.address}/>
            <DatePickerContainer label="Ngày sinh:" byData={changeBirthDay} value={data.birthDay}/>
            <RadioContainer label="Giới tính:" byData={changeGender} value={data.gender}/>
            <SelectedBoxContainer label="Chức vụ:" byData={changeRole} data={roles} value={data.role}/>
            <ButtonContainer bindEventEdit={edit} />
        </Container>
    );
}

const mapStateToProps = state => {
    console.log("state in new Booktitle", state);
    return {
        roles: state.roles,
    }
}

const mapDispatchToProps = (dispatch, props) => {
    return {

    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Form);