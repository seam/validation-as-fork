/**
 * JBoss, Home of Professional Open Source
 *
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.seam.validation.method.service;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jboss.seam.validation.AutoValidating;
import org.jboss.seam.validation.method.domain.Movie;

/**
 * An exemplary business service for which automatic method-level validation is
 * enabled.
 * 
 * @author Gunnar Morling
 * 
 */
@AutoValidating
public class MovieRepository
{

   private final Map<Long, Movie> sampleMovies = new TreeMap<Long, Movie>();

   public MovieRepository()
   {

      Movie movie = new Movie(1, "The Usual Suspects", 106, "Bryan Singer", new GregorianCalendar(1995, 7, 16).getTime());

      sampleMovies.put(movie.getId(), movie);

      movie = new Movie(2, "The Road", 160, "John Hillcoat", null);

      sampleMovies.put(movie.getId(), movie);
   }

   public @NotNull
   @Valid
   Set<Movie> findMoviesByDirector(@NotNull @Size(min = 3) String director)
   {

      Set<Movie> theValue = new HashSet<Movie>();

      for (Movie oneMovie : sampleMovies.values())
      {
         if (oneMovie.getDirector().equals(director))
         {
            theValue.add(oneMovie);
         }

      }

      return theValue;
   }

}