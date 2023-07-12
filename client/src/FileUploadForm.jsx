import React, { useState } from 'react';
import axios from 'axios';
import "./styles.css";

const FileUploadForm = ({ onUpload, setWelcomeEnabled,setUpload }) => {
  const [file, setFile] = useState(null);
  const [description, setDescription] = useState('');

  const [content, setContent] = useState('');

  const handleFileRead = (e) => {
      const fileContent = e.target.result;
      setContent(fileContent);
      localStorage.setItem(localStorage.getItem("fileName"), JSON.stringify(fileContent));
  };

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
    const selectedFile = e.target.files[0];
    const reader = new FileReader();
    localStorage.setItem("fileName", e.target.files[0].name);
    reader.onloadend = handleFileRead;
    reader.readAsText(selectedFile);
  };

  const handleDescriptionChange = (e) => {
    setDescription(e.target.value);
  };

  const handleUpload = async () => {
    try {

      const body = {
        "fileName": file.name || `${localStorage.getItem("user")}_${new Date().getTime()}`,
        "operations": {
          "comments":[],
          "others":[{
            "type": "FILE_CREATED",
            "user": localStorage.getItem("user") || "system",
            "change": description,
            "date": new Date()
          }]
        }
      }

      await axios.post('http://localhost:8081/create-fileOperations', body);

      onUpload();
      setFile(null);
      setDescription('');
    } catch (error) {
      console.error('Error:', error);
      alert('File upload failed. Please try again.');
    }
  };

  const handleMainMenu = async () => {
    setWelcomeEnabled(true);
    setUpload(false);
  }

  return (
    <div className="app">
    <div className="view-modal">

      <h1>File Upload</h1>
      <input type="file" onChange={handleFileChange} />
      <br/>
      <br/>
      <p>Please provide a file description</p>
      <input type="text" placeholder="Please provide a Description" value={description} onChange={handleDescriptionChange} />
      <hr/>
      <br/>
      <center>
      <button className="button" onClick={handleUpload}>Upload</button>
      <br/>
      <button className="button" onClick={handleMainMenu}>Main Menu</button>
      </center>
    </div>
    </div>
  );
};

export default FileUploadForm;
