import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import InputConTainer from './InputContainer'
import DatePickerContainer from './DatePickerContainer'
import RadioContainer from './RadioContainer'
import SelectedBoxContainer from './SelectedBoxContainer'
import ButtonContainer from './ButtonContainer';

function Form(props) {
    return (
        <Container>
            <InputConTainer label="Tên:"/>
            <InputConTainer label="Tên tài khoản:"/>
            <InputConTainer label="Mật khẩu:" is_password = {true}/>
            <InputConTainer label="Địa chỉ:"/>
            <DatePickerContainer label="Ngày sinh:"/>
            <RadioContainer label="Giới tính:"/>
            <SelectedBoxContainer label="Chức vụ:" />
            <ButtonContainer />
        </Container>
    );
}

export default Form;