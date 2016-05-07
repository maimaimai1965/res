<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="resume" tagdir="/WEB-INF/tags" %>

<div class="container">
	<div class="row">
		<div class="col-md-4 col-sm-6">
			<resume:person-main />
			<resume:person-languages />
			<resume:person-hobbies />
			<resume:person-info />
		</div>
		<div class="col-md-8 col-sm-6">
			<resume:person-objective/>
			<resume:person-skills />
			<resume:person-practics />
			<resume:person-certificates/>
			<resume:person-cources/>
			<resume:person-education />
		</div>
	</div>
</div>