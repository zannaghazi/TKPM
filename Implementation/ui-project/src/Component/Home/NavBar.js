import React from 'react';
import { Navbar, Nav, NavDropdown, Form, FormControl, Button } from 'react-bootstrap';
import styles from './static/styles.module.css'
import 'bootstrap/dist/css/bootstrap.min.css';

function NavBar(props) {
    return (
        <Navbar bg="dark" expand="lg" variant="dark">
            <Navbar.Brand href="#home" className={styles.mySpacing}>Zannaghazi-Libary</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="mr-auto">
                    <Nav.Link href="#home" className={[styles.myDropdown,styles.myBorderRight].join(" ")}>Trang chủ</Nav.Link>
                    <NavDropdown title="Sách bộ môn" id="basic-nav-dropdown" className={[styles.myDropdown,styles.myBorderRight].join(" ")}>
                        <NavDropdown.Item href="#action/3.1">Toán-Tin</NavDropdown.Item>
                        <NavDropdown.Item href="#action/3.2">Công nghệ thông tin</NavDropdown.Item>
                        <NavDropdown.Item href="#action/3.4">Điện tử viễn thông</NavDropdown.Item>
                        <NavDropdown.Divider />
                        <NavDropdown.Item href="#action/3.3">Hóa học</NavDropdown.Item>
                    </NavDropdown>
                    <NavDropdown title="Sách tham khảo" id="basic-nav-dropdown" className={[styles.myDropdown,styles.myBorderRight].join(" ")}>
                        <NavDropdown.Item href="#action/3.1">Sinh tồn</NavDropdown.Item>
                        <NavDropdown.Item href="#action/3.2">Khoa học viễn tưởng</NavDropdown.Item>
                        <NavDropdown.Item href="#action/3.3">Đời sống</NavDropdown.Item>
                        <NavDropdown.Item href="#action/3.4">Xã hội</NavDropdown.Item>
                    </NavDropdown>
                    <NavDropdown title="Truyện" id="basic-nav-dropdown" className={[styles.myDropdown,styles.myBorderRight].join(" ")}>
                        <NavDropdown.Item href="#action/3.1">Trinh thám</NavDropdown.Item>
                        <NavDropdown.Item href="#action/3.2">Kinh dị</NavDropdown.Item>
                        <NavDropdown.Item href="#action/3.3">Truyện cười</NavDropdown.Item>
                        <NavDropdown.Item href="#action/3.4">Cổ tích dân gian</NavDropdown.Item>
                    </NavDropdown>
                    <NavDropdown title="Báo chí" id="basic-nav-dropdown" className={styles.myDropdown}>
                        <NavDropdown.Item href="#action/3.1">Pháp luật</NavDropdown.Item>
                        <NavDropdown.Item href="#action/3.2">Kinh tế</NavDropdown.Item>
                        <NavDropdown.Item href="#action/3.3">Văn hóa-Xã hội</NavDropdown.Item>
                        <NavDropdown.Item href="#action/3.4">Du lịch</NavDropdown.Item>
                    </NavDropdown>
                </Nav>
                <Form inline>
                    <FormControl type="text" placeholder="Search" className="mr-sm-2" />
                    <Button variant="outline-success">Search</Button>
                </Form>
            </Navbar.Collapse>
        </Navbar>
    );
}

export default NavBar;