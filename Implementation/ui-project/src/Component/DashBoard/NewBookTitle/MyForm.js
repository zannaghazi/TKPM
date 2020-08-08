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
            <InputConTainer label="ISBN:"/>
            <InputConTainer label="Vị trí:"/>
            <SelectedBoxContainer label="Tác giả:"/>
            <SelectedBoxContainer label="Thể loại:"/>
            <DatePickerContainer label="Ngày phát hành:"/>
            <ButtonContainer />
        </Container>
    );
}

export default Form;