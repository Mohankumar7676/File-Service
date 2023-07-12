import React, { useEffect, useState } from 'react';
import axios from 'axios';
import FileItems from './FileItems';
import "./styles.css";

const OperationTrackingPanel = ({setWelcomeEnabled,setViewFiles}) => {
    const [files, setFiles] = useState([]);

    useEffect(() => {
        loadFiles();
    }, []);

    const loadFiles = async () => {
        try {

        const response = await axios.get('http://localhost:8081/getAll');
        setFiles(response.data);
        } catch (error) {
        console.error('Error:', error);
        alert('Failed to retrieve files.');
        }
    };

    const handleMainMenu = async () => {
        setWelcomeEnabled(true);
        setViewFiles(false);
    }

    return (
        <div className="view-modal">
        <center><h1>Uploaded Files</h1>
        <br/>
        <button className="button" onClick={handleMainMenu}>Main Menu</button>
        </center>
        <ul>
            {files.length == 0 && <center><hr/><h4>No uploaded Files Found</h4></center>}
            {files.map((file) => (
            <FileItems key={file.id} file={file} />
            ))}
        </ul>
        </div>    );
};

export default OperationTrackingPanel;
