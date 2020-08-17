import React, { useEffect } from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import InputConTainer from './InputContainer'
import ButtonContainer from './ButtonContainer';
import { useState } from 'react';
import config from '../../../asset/config.json'

function Form(props) {
    const [defaultValue, setDefaultValue] = useState({});
    const [durationTime, setDurationTime] = useState(0);
    const [maxBook, setMaxBook] = useState(0);
    const [maxTime, setMaxTime] = useState(0);

    useEffect(() => {
        let url = config.severAPi.hostUrl + ":8083/system/get_all_system_const";
        fetch(url, {
            method: "get",
            headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
        })
            .then(res => res.json())
            .then(dataReceive => {
                console.log("123123123", dataReceive);
                setDefaultValue(dataReceive);
                setDurationTime(dataReceive.durationTime);
                setMaxBook(dataReceive.maxBook);
                setMaxTime(dataReceive.maxTime);
            });
    }, []);

    function update() {
        let body = {
            durationTime,
            maxBook,
            maxTime
        }
        let url = config.severAPi.hostUrl + ":8083/system/update_system_const";
        fetch(url, {
            method: "post",
            headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
            body: JSON.stringify(body)
        })
            .then(res => res.json())
            .then(dataReceive => {
                if (dataReceive.result) {
                    alert(dataReceive.message)
                }
                else {
                    alert("Đã có lỗi xảy ra. Vui lòng thử lại!");
                }
            });
    }

    function cancel() {
        setDurationTime(defaultValue.durationTime);
        setMaxBook(defaultValue.maxBook);
        setMaxTime(defaultValue.maxTime);
    }

    return (
        <Container>
            <InputConTainer label="Thời hạn chung của thẻ:" byData={setDurationTime} value={durationTime} />
            <InputConTainer label="Số sách mượn tối đa:" byData={setMaxBook} value={maxBook} />
            <InputConTainer label="Số ngày mượn tối đa:" byData={setMaxTime} value={maxTime} />
            <ButtonContainer byEventUpdate={update} byEventCancel = {cancel} />
        </Container>
    );
}

export default Form;