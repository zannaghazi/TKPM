import React from 'react';
import {Table} from 'react-bootstrap';

function TableContainer(props) {
    return (
        <Table striped bordered hover>
            <thead>
                <tr>
                    <th width={'2%'}>#</th>
                    <th width={'15%'}>Tên</th>
                    <th width={'15%'}>Tên tài khoản</th>
                    <th width={'10%'}>Ngày sinh</th>
                    <th>Địa chỉ</th>
                    <th width={'10%'}>Giới tính</th>
                    <th width={'8%'}>
                    </th>
                </tr>
            </thead>
            <tbody>
                
            </tbody>
        </Table>
    );
}

export default TableContainer;