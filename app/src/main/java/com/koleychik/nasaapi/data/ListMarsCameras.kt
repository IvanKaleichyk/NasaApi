package com.koleychik.nasaapi.data

import com.koleychik.nasaapi.models.CameraModel

val listCameras = listOf(
    CameraModel("Front Hazard Avoidance Camera", "FHAZ"),
    CameraModel("Rear Hazard Avoidance Camera", "RHAZ"),
    CameraModel("Mast Camera", "MAST"),
    CameraModel("Chemistry and Camera Complex", "CHEMCAM"),
    CameraModel("Mars Hand Lens Imager", "MAHLI"),
    CameraModel("Mars Descent Imager", "MARDI"),
    CameraModel("Navigation Camera", "NAVCAM"),
    CameraModel("Panoramic Camera", "PANCAM"),
    CameraModel("Miniature Thermal Emission Spectrometer (Mini-TES)", "MINITES")
)