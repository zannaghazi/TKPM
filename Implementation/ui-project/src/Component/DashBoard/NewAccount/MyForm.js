import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import styles from '../static/styles.module.css';
import InputConTainer from './InputContainer'

function Form(props) {
    return (
        <Container>
            <InputConTainer label="Tên:"/>
            <InputConTainer label="Tên tài khoản:"/>
            <InputConTainer label="Địa chỉ"/>
        </Container>
    );
}

export default Form;