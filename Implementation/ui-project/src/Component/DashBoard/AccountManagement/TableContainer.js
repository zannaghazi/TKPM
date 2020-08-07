import React from 'react';
import { Table, Button } from 'react-bootstrap';
import styles from '../static/styles.module.css'

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
                <tr>
                    <td>1</td>
                    <td>Võ Thanh Hiếu</td>
                    <td>1412168</td>
                    <td>13/07/1996</td>
                    <td>33/29 ĐHT 21 phường Đông Hưng Thuận quận 12 tp Hồ Chí Minh</td>
                    <td>Nam</td>
                    <td>
                        <Button variant="primary" className={styles.myButtonDetail}><i className="fa fa-edit"></i></Button>
                        <Button variant="danger" className={[styles.myButtonDetail, "ml-2"].join(" ")}><i className="fa fa-trash"></i></Button>
                    </td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Nguyễn Thái Hoàng</td>
                    <td>1412180</td>
                    <td>05/08/1996</td>
                    <td>c4/6d Lê Đình Chi huyện Bình Chánh tp Hồ Chí Minh</td>
                    <td>Nam</td>
                    <td>
                        <Button variant="primary" className={styles.myButtonDetail}><i className="fa fa-edit"></i></Button>
                        <Button variant="danger" className={[styles.myButtonDetail, "ml-2"].join(" ")}><i className="fa fa-trash"></i></Button>
                    </td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Phan Dương Phi</td>
                    <td>1412180</td>
                    <td>22/10/1996</td>
                    <td>799 Trần Xuân Soạn phường Tân Hưng quận 7 tp Hồ Chí Minh</td>
                    <td>Nam</td>
                    <td>
                        <Button variant="primary" className={styles.myButtonDetail}><i className="fa fa-edit"></i></Button>
                        <Button variant="danger" className={[styles.myButtonDetail, "ml-2"].join(" ")}><i className="fa fa-trash"></i></Button>
                    </td>
                </tr>
            </tbody>
        </Table>
    );
}

export default TableContainer;