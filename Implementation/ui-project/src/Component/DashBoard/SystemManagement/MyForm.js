import React, { useEffect } from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import InputConTainer from './InputContainer'
import ButtonContainer from './ButtonContainer';
import { useState } from 'react';
import config from '../../../asset/config.json'
import { connect } from 'react-redux';
import * as actions from '../../../actions/index';

function Form(props) {
    const [defaultValue, setDefaultValue] = useState({});
    const [durationTime, setDurationTime] = useState(0);
    const [maxBook, setMaxBook] = useState(0);
    const [maxTime, setMaxTime] = useState(0);

    useEffect(() => {
        setDefaultValue(props.systems);
        setDurationTime(props.systems.durationTime);
        setMaxBook(props.systems.maxBook);
        setMaxTime(props.systems.maxTime);
    }, [props.systems]);

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
            <ButtonContainer byEventUpdate={update} byEventCancel={cancel} />
        </Container>
    );
}

const mapStateToProps = state => {
    return {
        systems: state.systems
    }
}

const mapDispatchToProps = (dispatch, props) => {
    return {
        onSetSystem: (system) => {
            dispatch(actions.setSystem(system));
        }
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Form);