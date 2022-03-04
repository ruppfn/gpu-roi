<div id="top"></div>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]




<h3 align="center">gpu-roi</h3>

  <p align="center">
    <br />
    <a href="https://github.com/ruppfn/gpu-roi"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://gpu-roi.herokuapp.com/">View Demo</a>
    ·
    <a href="https://github.com/ruppfn/gpu-roi/issues">Report Bug</a>
    ·
    <a href="https://github.com/ruppfn/gpu-roi/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

Web App to find GPUs in local retailers with low ROI.
<p align="right">(<a href="#top">back to top</a>)</p>



### Built With

* [Java 16](https://www.java.com/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [React.js](https://reactjs.org/)
* [Heroku](https://heroku.com/)

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

* MySQL database in `localhost:3306/GPU_ROI` with user `root` and password `pass` (you can change this in [application-local.yml](src/main/resources/application-local.yml))

### Installation

1.Clone the repo
   ```sh
   git clone https://github.com/ruppfn/gpu-roi.git
   ```

2.Install NPM packages and build the app
  ```sh
  npm install 
  npm run build
  ```

3.Run the backend
   ```sh
  ./mvnw spring-boot:run -Dspring-boot.run.profiles=local
  ```

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- USAGE EXAMPLES -->
## Usage
1.Synchronize devices
  ```sh
  curl -X POST http://localhost:8080/api/devices
  ```
2.Synchronize devices prices
  ```sh
  curl -X POST http://localhost:8080/api/devices/prices
  ```

3.Synchronize USD & BTC prices
  ```sh
  curl -X POST http://localhost:8080/api/prices
  ```

4.Open `http://localhost:8080/`

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- ROADMAP -->
## Roadmap

- Migrate to AWS Serverless (AWS Solutions Architect & Developer Associate practice)
  - Frontend in CloudFront
  - ~~Migrate MySQL to DynamoDB~~
  - ~~Synchronization Lambdas~~
    - ~~USD & BTC Prices~~
    - ~~Devices~~
    - ~~Devices prices~~
  - ~~EventBridge rule to run lambdas~~
  - [Device Controller](src/main/java/ar/com/frupp/gpuroi/controller/DeviceController.java) lambdas

See the [open issues](https://github.com/ruppfn/gpu-roi/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- CONTACT -->
## Contact

Franco Nahuel Rupp - ruppfn@gmail.com

Project Link: [https://github.com/ruppfn/gpu-roi](https://github.com/ruppfn/gpu-roi)

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/ruppfn/gpu-roi.svg?style=for-the-badge
[contributors-url]: https://github.com/ruppfn/gpu-roi/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/ruppfn/gpu-roi.svg?style=for-the-badge
[forks-url]: https://github.com/ruppfn/gpu-roi/network/members
[stars-shield]: https://img.shields.io/github/stars/ruppfn/gpu-roi.svg?style=for-the-badge
[stars-url]: https://github.com/ruppfn/gpu-roi/stargazers
[issues-shield]: https://img.shields.io/github/issues/ruppfn/gpu-roi.svg?style=for-the-badge
[issues-url]: https://github.com/ruppfn/gpu-roi/issues
[license-shield]: https://img.shields.io/github/license/ruppfn/gpu-roi.svg?style=for-the-badge
[license-url]: https://github.com/ruppfn/gpu-roi/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/ruppfn