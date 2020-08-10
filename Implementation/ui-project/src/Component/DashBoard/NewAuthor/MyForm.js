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
            <ButtonContainer />
        </Container>
    );
}

export default Form;