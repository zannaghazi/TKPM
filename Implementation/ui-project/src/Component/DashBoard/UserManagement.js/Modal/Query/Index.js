import React, {useState} from 'react';
import {Modal} from 'react-bootstrap';
import Body from './Body';

function UserModal(props) {
    const [show, setShow] = useState(true);
    return (
        <Modal
            size="lg"
            show={show}
            onHide={() => setShow(false)}
            aria-labelledby="example-modal-sizes-title-lg"
        >
            <Modal.Header closeButton>
                <Modal.Title id="example-modal-sizes-title-lg">
                    Tìm kiếm tài khoản
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Body/>
            </Modal.Body>
        </Modal>
    );
}

export default UserModal;