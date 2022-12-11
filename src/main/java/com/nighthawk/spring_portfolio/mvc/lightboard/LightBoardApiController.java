package com.nighthawk.spring_portfolio.mvc.lightboard;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lightboard")
public class LightBoardApiController {
    @GetMapping("/{numRows}/{numCols}")
    public ResponseEntity<String> lightboard(@PathVariable int numRows, @PathVariable int numCols) {
        try {
            LightBoard lightboard = new LightBoard(numRows, numCols);
            return new ResponseEntity<>(lightboard.toString(), HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Error/Parsing Error, check your input", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/toTerminal/{numRows}/{numCols}")
    public ResponseEntity<String> toTerminal (@PathVariable int numRows, @PathVariable int numCols) {
        try {
            LightBoard lightboard = new LightBoard(numRows, numCols);
            return new ResponseEntity<>(lightboard.toTerminal(), HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Error/Parsing Error, check your input", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("toColorPalette/{numRows}/{numCols}/{cellWidth}/{cellHeight}")
    public ResponseEntity<String> toColorPalette(@PathVariable int numRows, @PathVariable int numCols, @PathVariable int cellWidth, @PathVariable int cellHeight) {
        try {
            LightBoard lightboard = new LightBoard(numRows, numCols);
            return new ResponseEntity<>(lightboard.toColorPalette(cellWidth, cellHeight), HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Error/Parsing Error, check your input", HttpStatus.BAD_REQUEST);
        }
    }
}
